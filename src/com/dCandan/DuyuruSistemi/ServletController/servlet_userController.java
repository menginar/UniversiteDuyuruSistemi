package com.dCandan.DuyuruSistemi.ServletController;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dCandan.DuyuruSistemi.DatabaseController.database_userController;
import com.dCandan.DuyuruSistemi.Model.tuser;

@WebServlet("/servlet_userController")
public class servlet_userController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	database_userController controller;
	Warnings warning = new Warnings();

	public servlet_userController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		controller = new database_userController();
		/*
		 * 	Bu sayfaya yonlendirilmis olan sayfalardaki formlar icerisine gizli 
		 * 	olarak yerlestirilen ve name ozelligine "islem" degeri atanmis 
		 * 	elemanlar okunur. Bu elemanin degerine gore form icin yapilmasi 
		 * 	gereken islem belirlenir ve yapilir.
		 */		
		String islem = request.getParameter("islem")+"";

		if (islem.equals("login")){
			try {
				Login(request, response);
			} catch (Exception e) {
				System.out.println("Login Isleminde Hata Olustu : "+e.getMessage());
			}
			finally{
				new VeriTemizligiYap();
			}
		}
		if (islem.equals("SifreDegis")){
			try {
				SifreDegis(request, response);
			} catch (Exception e) {
				System.out.println("Sifre Degisirken Hata Olustu : "+e.getMessage());
			}
		}
	}

	private void Login(HttpServletRequest request, HttpServletResponse response) 
			throws Exception{
		String userName = request.getParameter("username") + "";
		String password = request.getParameter("password") + "";
		HttpSession Session = request.getSession();

		if (isMalformed(userName) | isMalformed(password)){
			Session.setAttribute("basarisiz_islem", warning.getWarnings(1));
			response.sendRedirect("index.jsp");
		}
		else{
			tuser user = new tuser(userName, password, 0, 0);

			controller.isUser(user);
			/*	
			 *  Ustteki islem giris yapan kullanici egerki kayitli bir kullanici
			 *  ise o kullanicinin yetkiID'sini o kullanici icin olusturulan 
			 *  user nesnesine ekler.
			 */

			Session.setAttribute("username", user.getUsername());
			Session.setAttribute("password", user.getPassword());
			Session.setAttribute("isim", controller.IsimGetir("tuser_bilgi", userName));
			Session.setAttribute("yetkiID", user.getYetkiID());
			Session.setAttribute("gorevID", user.getGorevID());
			/*
			 * 	Ustteki kod blogunda giris yapan kullanicidan gelen bilgiler o 
			 * 	kullanici icin olusturulan session nesnesine kaydediliyor. 
			 * 	Bu sekilde veriler kaydedilerek kullanilmasi gereken her yerde 
			 * 	session nesnesinden okunarak islem yapilabiliyor.
			 */

			switch(user.getYetkiID()){
			/*
			 * 	isUser metodu araciligiyla kullanicinin yetkiID'sini belirlemistik.
			 * 	Bu yetkiID'sine gore yonlendirme yapilan blok asagidadir.Herkez 
			 * 	kendini ilgilendiren sayfalardan baskasini goremez.
			 */
			case 0://	Uye degil
				Session.setAttribute("basarisiz_islem", warning.getWarnings(0));
				response.sendRedirect("index.jsp");
				break;
			case 1://	Normal Kullanici
				response.sendRedirect("NormalKullanici/HomePage.jsp");
				break;
			case 2://	Reklam Yoneticisi
				Session.setAttribute("gorev", "Reklam Yoneticisi");
				response.sendRedirect("ReklamYoneticisi/HomePage.jsp");
				break;
			case 3://	Fakulte Yoneticisi
				Session.setAttribute("gorev", "Fakulte Yoneticisi");
				Session.setAttribute("fakulteAd", controller.IsimGetir("tfakulte", user.getGorevID()+""));
				response.sendRedirect("FakulteYoneticisi/HomePage.jsp");
				break;
			case 4://	Genel Yonetici
				Session.setAttribute("gorev", "Genel Yonetici");
				response.sendRedirect("GenelYonetici/HomePage.jsp");
				break;
			}
		}
	}

	private void SifreDegis(HttpServletRequest request, HttpServletResponse response) 
			throws Exception{
		String userName = request.getParameter("username") + "";
		String last_password = request.getParameter("last_password") + "";

		HttpSession Session = request.getSession();

		String password = request.getParameter("password")+"";
		String newPassword = request.getParameter("password2") + "";
		String sesPassword = Session.getAttribute("password")+"";

		if (isMalformed(last_password) | isMalformed(password) | isMalformed(newPassword))
			Session.setAttribute("malformed_data", warning.getWarnings(2));
		else{
			if (last_password.equals(sesPassword)){
				if (password.equals(newPassword)){
					tuser user = new  tuser(userName, password, 0, 0);
					user.setYetkiID(Integer.parseInt(Session.getAttribute("yetkiID")+""));
					user.setGorevID(Integer.parseInt(Session.getAttribute("gorevID")+""));

					if (controller.SifreDegis(user)){
						Session.setAttribute("basarili_islem", warning.getWarnings(3));
						Session.setAttribute("password", password);
					}
					else
						Session.setAttribute("basarisiz_islem", warning.getWarnings(4));
				}
				else
					Session.setAttribute("basarisiz_islem", warning.getWarnings(12));
			}
			else
				Session.setAttribute("basarisiz_islem", warning.getWarnings(5));
		}
		if (Session.getAttribute("gorev").equals("Fakulte Yoneticisi"))
			response.sendRedirect("FakulteYoneticisi/HomePage.jsp");
		if (Session.getAttribute("gorev").equals("Reklam Yoneticisi"))
			response.sendRedirect("ReklamYoneticisi/HomePage.jsp");
		if (Session.getAttribute("gorev").equals("Genel Yonetici"))
			response.sendRedirect("GenelYonetici/HomePage.jsp");
	}

	private boolean isMalformed(String data){
		data = data.trim();
		if ((data == null) | (data.equals("")) | (data.equals(null+"")))
			return true;
		return false;
	}
}
