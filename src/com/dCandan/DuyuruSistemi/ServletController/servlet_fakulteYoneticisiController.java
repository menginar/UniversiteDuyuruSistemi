package com.dCandan.DuyuruSistemi.ServletController;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dCandan.DuyuruSistemi.DatabaseController.database_fakulteYoneticisiController;
import com.dCandan.DuyuruSistemi.Model.tduyuru_bilgi;
import com.dCandan.DuyuruSistemi.Model.tduyuru_icerik;

@WebServlet("/servlet_fakulteYoneticisiController")
public class servlet_fakulteYoneticisiController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	database_fakulteYoneticisiController controller = new database_fakulteYoneticisiController();
	Warnings warning = new Warnings();

	boolean islemBasarimi = false;

	public servlet_fakulteYoneticisiController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String islem = request.getParameter("islem")+"";
		try{
			if (islem.equals("DuyuruEkle")){
				DuyuruEkle(request, response);
			}
			if (islem.equals("DuyuruSil")){
				DuyuruSil(request, response);
			}
			if (islem.equals("DuyuruYayinIstek")){
				DuyuruIstegiYap(request, response);
			}
			if (islem.equals("DuyuruDuzenle")){
				DuyuruDuzenle(request, response);
			}
		}catch(Exception e){
			request.getSession().setAttribute("basarisiz_islem", warning.getWarnings(0)+"\n"+warning.getWarnings(6));
		}
		finally{
			response.sendRedirect("FakulteYoneticisi/HomePage.jsp");
		}
	}

	private void DuyuruEkle(HttpServletRequest request, HttpServletResponse response) 
			throws Exception{
		HttpSession Session = request.getSession();
		int duyuruID, duyuruTurID, fakulteID, bolumID;
		String duyuruBaslik, duyuruIcerik, duyuruBaslangic, duyuruBitis;

		duyuruID = Integer.parseInt(request.getParameter("duyuruID")+"");
		fakulteID = Integer.parseInt(request.getParameter("fakulteID")+"");
		bolumID = Integer.parseInt(request.getParameter("bolumID")+"");
		duyuruTurID = Integer.parseInt(request.getParameter("duyuruTur")+"");
		duyuruBaslik = request.getParameter("duyuruBaslik")+"";
		duyuruIcerik = request.getParameter("duyuruIcerik")+"";
		duyuruBaslangic = request.getParameter("baslangicTarih")+"";
		duyuruBitis = request.getParameter("bitisTarih")+"";

		if (isMalformedData(duyuruID) | isMalformedData(fakulteID) 
				| isMalformedData(bolumID) | isMalformedData(duyuruBaslik) 
				| isMalformedData(duyuruIcerik) | isMalformedData(duyuruBaslangic) 
				| isMalformedData(duyuruBitis)){
			Session.setAttribute("malformed_data", warning.getWarnings(2));
		}
		else{
			if (duyuruIcerik.length() > 499)
				Session.setAttribute("malformed_data", warning.getWarnings(7));
			else{
				tduyuru_bilgi DuyuruBilgi 
				= new tduyuru_bilgi(duyuruID, fakulteID, bolumID, duyuruTurID);
				tduyuru_icerik DuyuruIcerik 
				= new tduyuru_icerik(duyuruID, duyuruBaslik, duyuruIcerik, 
						duyuruBaslangic, duyuruBitis);

				if (controller.DuyuruEkle(DuyuruBilgi, "tduyuru_bilgi", null)){
					if (controller.DuyuruEkle(null, "tduyuru_icerik", DuyuruIcerik))
						islemBasarimi = true;
					else
						islemBasarimi = false;
				}
				else
					islemBasarimi = false;

				if (islemBasarimi)
					Session.setAttribute("basarili_islem", warning.getWarnings(3)+"\n"+warning.getWarnings(11));
				else
					Session.setAttribute("basarisiz_islem", warning.getWarnings(4));
			}
		}
	}

	private void DuyuruSil(HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		HttpSession Session = request.getSession();
		int duyuruID = 0;

		ArrayList<tduyuru_bilgi> SilinecekDuyuruBilgileri = new ArrayList<>();
		ArrayList<tduyuru_icerik> SilinecekDuyuruIcerikleri = new ArrayList<>();
		int duyuruAdedi = Integer.parseInt(request.getParameter("duyuruAdet"));

		for (int i = 0 ; i <= duyuruAdedi ; i++){
			String secilenDuyuru = request.getParameter("duyuru"+i)+"";
			if (secilenDuyuru.equals("on")){
				duyuruID = Integer.parseInt(request.getParameter("duyuruID"+i)+"");

				tduyuru_bilgi DuyuruBilgi = new tduyuru_bilgi(duyuruID, 0, 0, 0);
				tduyuru_icerik DuyuruIcerik 
				= new tduyuru_icerik(duyuruID, null, null, null, null);

				SilinecekDuyuruBilgileri.add(DuyuruBilgi);
				SilinecekDuyuruIcerikleri.add(DuyuruIcerik);

				DuyuruBilgi = null;
				DuyuruIcerik = null;
			}
		}
		if (SilinecekDuyuruBilgileri.size() == 0)
			islemBasarimi = false;
		for (tduyuru_bilgi DuyuruBilgi : SilinecekDuyuruBilgileri){
			for (tduyuru_icerik DuyuruIcerik : SilinecekDuyuruIcerikleri){
				if (DuyuruBilgi.getDuyuruID() == DuyuruIcerik.getDuyuruID()){
					if (controller.DuyuruSil(DuyuruBilgi, "tduyuru_bilgi", null)){
						if (controller.DuyuruSil(null, "tduyuru_icerik", DuyuruIcerik))
							islemBasarimi = true;
						else
							islemBasarimi = false;
					}
					else
						islemBasarimi = false;
					break;
				}
			}
		}
		if (islemBasarimi)
			Session.setAttribute("basarili_islem", warning.getWarnings(3));
		else
			Session.setAttribute("basarisiz_islem", warning.getWarnings(4));
	}

	private void DuyuruDuzenle(HttpServletRequest request, HttpServletResponse response) 
			throws Exception{
		HttpSession Session = request.getSession();

		int duyuruID, duyuruTurID, fakulteID, bolumID;
		String duyuruBaslik, duyuruIcerik, duyuruBaslangic, duyuruBitis;

		String islemBelirtec = request.getParameter("islemBelirtec")+"";
		String islem = islemBelirtec.substring(0, islemBelirtec.length()-1);

		if (islem.equals("Duzenle")){
			int duyuruNo = Integer
					.parseInt(islemBelirtec.substring(islemBelirtec.length()-1, 
							islemBelirtec.length()));
			duyuruID = Integer
					.parseInt(request.getParameter("duyuruID"+duyuruNo)+"");

			Session.setAttribute("duyuruID", duyuruID);
			Session.setAttribute("secilenIslem", "DuyuruDuzenle_DuyuruIcerik");
		}
		if (islem.equals("IcerikDuzenle")){
			duyuruID = Integer.parseInt(request.getParameter("duyuruID")+"");
			fakulteID = Integer.parseInt(request.getParameter("fakulteID")+"");
			bolumID = Integer.parseInt(request.getParameter("bolumID")+"");
			duyuruTurID = 0;
			duyuruBaslik = request.getParameter("duyuruBaslik")+"";
			duyuruIcerik = request.getParameter("duyuruIcerik")+"";
			duyuruBaslangic = request.getParameter("baslangicTarih")+"";
			duyuruBitis = request.getParameter("bitisTarih")+"";

			if (isMalformedData(duyuruBaslik) | isMalformedData(duyuruIcerik) 
					| isMalformedData(duyuruBaslangic) | isMalformedData(duyuruBitis)){
				Session.setAttribute("malformed_data", warning.getWarnings(2));
			}
			else{
				tduyuru_bilgi DuyuruBilgi 
				= new tduyuru_bilgi(duyuruID, fakulteID, bolumID, duyuruTurID);
				tduyuru_icerik DuyuruIcerik 
				= new tduyuru_icerik(duyuruID, duyuruBaslik, duyuruIcerik, 
						duyuruBaslangic, duyuruBitis);

				if (controller.DuyuruDuzenle(DuyuruBilgi, "tduyuru_bilgi", null)){
					if (controller.DuyuruDuzenle(null, "tduyuru_icerik", DuyuruIcerik))
						islemBasarimi = true;
					else
						islemBasarimi = false;
				}
				else
					islemBasarimi = false;

				if (islemBasarimi)
					Session.setAttribute("basarili_islem", warning.getWarnings(3)+"\n"+warning.getWarnings(11));
				else
					Session.setAttribute("basarisiz_islem", warning.getWarnings(4));
			}
		}
	}

	private void DuyuruIstegiYap(HttpServletRequest request, HttpServletResponse response) 
			throws Exception{

		HttpSession Session = request.getSession();

		int duyuruID, fakulteID, bolumID;

		String secilenDuyuruID = request.getParameter("duyuruID")+"";
		String secilenFakulteID = request.getParameter("fakulteID")+"";
		String secilenBolumID = request.getParameter("bolumID")+"";

		int yeniDuyuruID = controller.DuyuruIDHesapla();

		if (isMalformedData(secilenDuyuruID) | isMalformedData(secilenFakulteID) 
				| isMalformedData(secilenBolumID)){
			Session.setAttribute("malformed_data", warning.getWarnings(2));
		}
		else{
			duyuruID = Integer.parseInt(secilenDuyuruID);
			fakulteID = Integer.parseInt(secilenFakulteID);
			bolumID = Integer.parseInt(secilenBolumID);
			ArrayList<tduyuru_icerik> DuyuruIcerikler = controller
					.DuyuruIcerikListele();
			tduyuru_bilgi DuyuruBilgi 
			= new tduyuru_bilgi(yeniDuyuruID, fakulteID, bolumID, 2);
			tduyuru_icerik DuyuruIcerik = null;

			for (tduyuru_icerik Duyuru_Icerik : DuyuruIcerikler){
				if (duyuruID == Duyuru_Icerik.getDuyuruID()){
					DuyuruIcerik = new tduyuru_icerik(
							yeniDuyuruID, Duyuru_Icerik.getDuyuruBaslik(), 
							Duyuru_Icerik.getDuyuruIcerik(), 
							Duyuru_Icerik.getDuyuruBaslangicTarih(),
							Duyuru_Icerik.getDuyuruBitisTarih());
					break;
				}
			}
			if (controller.DuyuruEkle(null, "tduyuru_icerik", DuyuruIcerik)){
				if (controller.DuyuruEkle(DuyuruBilgi, "tduyuru_bilgi", null))
					islemBasarimi = true;
				else
					islemBasarimi = false;
			}
			else
				islemBasarimi = false;

			if (islemBasarimi)
				Session.setAttribute("basarili_islem", warning.getWarnings(3)+"\n"+warning.getWarnings(11));
			else
				Session.setAttribute("basarisiz_islem", warning.getWarnings(4));
		}
	}

	private boolean isMalformedData(String data){
		data = data.trim();
		if (data.equals(null) | data.equals("") 
				| data.equals(null+"") | data.equals(" "))
			return true;
		return false;
	}

	private boolean isMalformedData(int data){
		if (data == 0)
			return true;
		return false;
	}
}
