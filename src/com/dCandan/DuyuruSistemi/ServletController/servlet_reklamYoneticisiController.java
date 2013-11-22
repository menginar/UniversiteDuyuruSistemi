package com.dCandan.DuyuruSistemi.ServletController;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.dCandan.DuyuruSistemi.DatabaseController.database_reklamYoneticisiController;
import com.dCandan.DuyuruSistemi.Model.treklam;


@WebServlet("/servlet_reklamYoneticisiController")
public class servlet_reklamYoneticisiController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	database_reklamYoneticisiController controller = new database_reklamYoneticisiController();
	Warnings warning = new Warnings();

	public servlet_reklamYoneticisiController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession Session = request.getSession();
		String islem = request.getParameter("islem")+"";
		try{
			if (isMalformedData(islem))
				ReklamEkle(request);
			if (islem.equals("ReklamDuzenle") | islem.equals("ReklamDuzenle_ReklamIcerik"))
				ReklamDuzenle(request, response);
		}catch(Exception e){
			Session.setAttribute("basarisiz_islem", warning.getWarnings(2));
		}
		finally{
			response.sendRedirect("ReklamYoneticisi/HomePage.jsp");
		}
	}

	private void ReklamEkle(HttpServletRequest request) throws Exception{
		HttpSession Session = request.getSession();

		int reklamID = controller.reklamIDHesapla();
		String filePath = getServletContext().getRealPath("\\ReklamYoneticisi\\ReklamResimleri\\")+"\\";
		String fileName = "Reklam"+reklamID+".jpg";

		try{
			File file;
			int maxFileSize = 5000 * 1024;
			int maxMemSize = 5000 * 1024;			

			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(maxMemSize);
			factory.setRepository(new File(".\\temp"));

			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setSizeMax( maxFileSize );

			List<FileItem> uploadItems = upload.parseRequest(request);

			String reklamIcerik = null, baslangicTarih = null, bitisTarih = null;

			for (FileItem uploadItem : uploadItems){
				if (uploadItem.isFormField () )	{
					String fieldName = uploadItem.getFieldName();

					if (fieldName.equals("baslangicTarih"))
						baslangicTarih = uploadItem.getString();
					if (fieldName.equals("bitisTarih"))
						bitisTarih = uploadItem.getString();
					if (fieldName.equals("reklamIcerik"))
						reklamIcerik = uploadItem.getString();
				}
				else{
					file = new File(filePath+fileName);
					uploadItem.write(file);
				}
			}
			ReklamBilgiEkle(reklamID, fileName, reklamIcerik, baslangicTarih, bitisTarih, Session.getAttribute("username")+"", Session);
		}catch(Exception e){
			Session.setAttribute("basarisiz_islem", warning.getWarnings(3));
		}
	}

	private boolean ReklamBilgiEkle(int reklamID, String resimPath, String reklamIcerik, String reklamBaslangic, String reklamBitis, String username, HttpSession Session){
		if (isMalformedData(resimPath) | isMalformedData(reklamIcerik) | isMalformedData(reklamBaslangic) | isMalformedData(reklamBitis)){
			Session.setAttribute("malformed_data", warning.getWarnings(2));
		}
		else{
			if (reklamIcerik.length() > 399){
				Session.setAttribute("malformed_data", warning.getWarnings(10));
			}
			else{
				treklam Reklam = new treklam(reklamID, reklamIcerik, reklamBaslangic, reklamBitis, "Reklam"+reklamID+".jpg", username, 0);
				if (controller.ReklamEkle(Reklam)){
					Session.setAttribute("basarili_islem", warning.getWarnings(3)+"\n"+warning.getWarnings(11));
					return true;
				}
				else
					Session.setAttribute("basarisiz_islem", warning.getWarnings(4));
			}
		}
		return false;
	}

	private void ReklamDuzenle(HttpServletRequest request, HttpServletResponse response) 
			throws Exception{
		HttpSession Session = request.getSession();

		String islem = request.getParameter("islem")+"";
		if (islem.equals("ReklamDuzenle")){
			String reklamBelirtec = request.getParameter("reklamBelirtec")+"";
			Session.setAttribute("reklamID", request.getParameter("reklamID"+reklamBelirtec));
			Session.setAttribute("secilenIslem", "ReklamDuzenle_ReklamIcerik");
		}
		if (islem.equals("ReklamDuzenle_ReklamIcerik")){
			Session.setAttribute("secilenIslem", "ReklamDuzenle_Reklamlar");
			int reklamID, yayinlanmaDurumu;
			String resimPath, reklamBaslangic, reklamBitis, reklamIcerik;

			reklamID = Integer.parseInt(request.getParameter("reklamID"));
			reklamIcerik = request.getParameter("reklamIcerik");
			reklamBaslangic = request.getParameter("baslangicTarih");
			reklamBitis = request.getParameter("bitisTarih");
			resimPath = request.getParameter("resimPath");
			yayinlanmaDurumu = 0;

			if (isMalformedData(reklamID) | isMalformedData(reklamIcerik) 
					| isMalformedData(resimPath) | isMalformedData(reklamBaslangic) 
					| isMalformedData(reklamBitis)){
				Session.setAttribute("malformed_data", warning.getWarnings(2));
			}
			else{
				treklam Reklam = new treklam(reklamID, reklamIcerik, 
						reklamBaslangic, reklamBitis, resimPath, 
						Session.getAttribute("username")+"", yayinlanmaDurumu);

				if (controller.ReklamDuzenle(Reklam))
					Session.setAttribute("basarili_islem", warning.getWarnings(3)+"\n"+warning.getWarnings(11));
				else
					Session.setAttribute("basarisiz_islem", warning.getWarnings(4));
			}
		}
	}

	private boolean isMalformedData(String data){
		data = data.trim();
		if (data.equals(null) | data.equals("") | data.equals(null+"") | data.equals(" "))
			return true;
		return false;
	}

	private boolean isMalformedData(int data){
		if (data == 0)
			return true;
		return false;
	}

}
