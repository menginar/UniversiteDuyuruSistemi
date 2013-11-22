package com.dCandan.DuyuruSistemi.ServletController;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dCandan.DuyuruSistemi.DatabaseController.database_genelYoneticiController;
import com.dCandan.DuyuruSistemi.Model.tduyuru_bilgi;
import com.dCandan.DuyuruSistemi.Model.tduyuru_icerik;
import com.dCandan.DuyuruSistemi.Model.treklam;
import com.dCandan.DuyuruSistemi.Model.tuser;
import com.dCandan.DuyuruSistemi.Model.tuser_bilgi;

@WebServlet("/servlet_genelYoneticiController")
public class servlet_genelYoneticiController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	database_genelYoneticiController controller = new database_genelYoneticiController();
	Warnings warning = new Warnings();

	boolean islemBasarimi = false;

	public servlet_genelYoneticiController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession Session = request.getSession();
		String islem = request.getParameter("islem")+"";
		try{
			if (islem.equals("ReklamYayiniDuzenle"))
				ReklamYayiniDuzenle(request);
			if (islem.equals("ReklamBilgisiDuzenle") | islem.equals("ReklamIcerik"))
				ReklamBilgisiDuzenle(request);
			if (islem.equals("DuyuruIslemleri")){
				String SonrakiIslem = Session.getAttribute("secilenIslem")+"";
				String fakulteID = request.getParameter("fakulteID")+"";

				if (isMalformedData(Integer.parseInt(fakulteID)))
					Session.setAttribute("malformed_data", warning.getWarnings(8));
				else{
					Session.setAttribute("fakulteID", fakulteID);

					if (SonrakiIslem.equals("DuyuruIslemleri_Fakulteler1")){
						Session.setAttribute("secilenIslem", "DuyuruYayiniDuzenle");
					}
					if (SonrakiIslem.equals("DuyuruIslemleri_Fakulteler2")){
						Session.setAttribute("secilenIslem", "DuyuruBilgisiDuzenle");
					}
				}
			}
			if (islem.equals("DuyuruYayiniDuzenle"))
				DuyuruYayiniDuzenle(request);
			if (islem.equals("DuyuruBilgisiDuzenle") | islem.equals("DuyuruIcerik"))
				DuyuruBilgisiDuzenle(request);
			if (islem.equals("KullaniciBilgisiDuzenle") | islem.equals("KullaniciBilgisi"))
				KullaniciBilgisiDuzenle(request);
			if (islem.equals("KullaniciEkle")){
				KullaniciEkle(request);
			}
		}catch (Exception e){
			request.getSession().setAttribute("basarisiz_islem", warning.getWarnings(4));
		}
		finally{
			response.sendRedirect("GenelYonetici/HomePage.jsp");
		}
	}

	private void ReklamYayiniDuzenle(HttpServletRequest request){
		HttpSession Session = request.getSession();
		int reklamID = 0, yayinlanmaDurumu;
		String resimPath, baslangicTarih, bitisTarih, reklamIcerik, username;

		String secilenReklam = request.getParameter("reklamBelirtec")+"";

		if (isMalformedData(secilenReklam))
			Session.setAttribute("malformed_data", warning.getWarnings(9));
		else{
			reklamID = Integer.parseInt(request.getParameter("reklamID"+secilenReklam));
			String islem = request.getParameter("islemBelirtec")+"";

			if (islem.equals("Onayla")){
				yayinlanmaDurumu = 1;
				resimPath = request.getParameter("resimPath"+secilenReklam)+"";
				baslangicTarih = request.getParameter("reklamBaslangic"+secilenReklam)+"";
				bitisTarih = request.getParameter("reklamBitis"+secilenReklam)+"";
				reklamIcerik = request.getParameter("reklamIcerik"+secilenReklam)+"";
				username = request.getParameter("username"+secilenReklam)+"";

				treklam Reklam = new treklam(reklamID, reklamIcerik, baslangicTarih, bitisTarih, resimPath, username, yayinlanmaDurumu);

				if (controller.ReklamDuzenle(Reklam))
					islemBasarimi = true;
				else
					islemBasarimi = false;
			}
			if (islem.equals("Onaylama")){
				treklam Reklam = new treklam(reklamID, null, null, null, null, null, 0);
				if (controller.ReklamSil(Reklam))
					islemBasarimi = true;
				else
					islemBasarimi = false;
			}

			if (islemBasarimi)
				Session.setAttribute("basarili_islem", warning.getWarnings(3));
			else
				Session.setAttribute("basarisiz_islem", warning.getWarnings(4));
		}
	}

	private void ReklamBilgisiDuzenle(HttpServletRequest request){
		HttpSession Session = request.getSession();

		int reklamID = 0, yayinlanmaDurumu;
		String resimPath, baslangicTarih, bitisTarih, reklamIcerik, username;

		String islem = request.getParameter("islem")+"";

		if (islem.equals("ReklamBilgisiDuzenle")){
			String SecilenReklam = request.getParameter("reklamBelirtec")+"";
			yayinlanmaDurumu = 1;
			reklamID = Integer.parseInt(request.getParameter("reklamID"+SecilenReklam));

			Session.setAttribute("secilenIslem", "ReklamIcerik");
			Session.setAttribute("reklamID", reklamID);			
		}
		if (islem.equals("ReklamIcerik")){
			reklamID = Integer.parseInt(request.getParameter("reklamID"));
			baslangicTarih = request.getParameter("baslangicTarih");
			bitisTarih = request.getParameter("bitisTarih");
			reklamIcerik = request.getParameter("reklamIcerik");
			resimPath = request.getParameter("resimPath");
			username = request.getParameter("username")+"";
			yayinlanmaDurumu = 1;

			if (isMalformedData(username) | isMalformedData(baslangicTarih) | isMalformedData(bitisTarih)
					| isMalformedData(reklamIcerik) | isMalformedData(resimPath)){
				Session.setAttribute("malformed_data", warning.getWarnings(2));
			}
			else{
				treklam Reklam = new treklam(reklamID, reklamIcerik, baslangicTarih, bitisTarih, resimPath, username, yayinlanmaDurumu);

				if (controller.ReklamDuzenle(Reklam))
					islemBasarimi = true;
				else
					islemBasarimi = false;
				if (islemBasarimi)
					Session.setAttribute("basarili_islem", warning.getWarnings(3));
				else
					Session.setAttribute("basarisiz_islem", warning.getWarnings(4));
			}
		}
	}

	private void DuyuruYayiniDuzenle(HttpServletRequest request){
		HttpSession Session = request.getSession();

		String islem = request.getParameter("islemBelirtec")+"";
		String SecilenDuyuru = request.getParameter("duyuruBelirtec")+"";

		int duyuruID, fakulteID, bolumID, duyuruTurID;
		String duyuruBaslik, duyuruIcerik, baslangicTarih, bitisTarih;

		tduyuru_bilgi DuyuruBilgi;
		tduyuru_icerik DuyuruIcerik;

		duyuruID = Integer.parseInt(request.getParameter("duyuruID"+SecilenDuyuru));

		if (islem.equals("Onayla")){
			fakulteID = Integer.parseInt(request.getParameter("fakulteID"+SecilenDuyuru)+"");
			bolumID = Integer.parseInt(request.getParameter("bolumID"+SecilenDuyuru));
			duyuruTurID = 1;
			duyuruBaslik = request.getParameter("duyuruBaslik"+SecilenDuyuru);
			duyuruIcerik = request.getParameter("duyuruIcerik"+SecilenDuyuru);
			baslangicTarih = request.getParameter("duyuruBaslangic"+SecilenDuyuru);
			bitisTarih = request.getParameter("duyuruBitis"+SecilenDuyuru);

			DuyuruBilgi = new tduyuru_bilgi(duyuruID, fakulteID, bolumID, duyuruTurID);
			DuyuruIcerik = new tduyuru_icerik(duyuruID, duyuruBaslik, duyuruIcerik, baslangicTarih, bitisTarih);

			if (controller.DuyuruDuzenle(DuyuruBilgi, "tduyuru_bilgi", null)){
				if (controller.DuyuruDuzenle(null, "tduyuru_icerik", DuyuruIcerik))
					islemBasarimi = true;
				else
					islemBasarimi = false;
			}
			else
				islemBasarimi = false;
		}
		if (islem.equals("Onaylama")){
			DuyuruBilgi = new tduyuru_bilgi(duyuruID, 0, 0, 0);
			DuyuruIcerik = new tduyuru_icerik(duyuruID, null, null, null, null);

			if (controller.DuyuruSil(DuyuruBilgi, "tduyuru_bilgi", null)){
				if (controller.DuyuruSil(null, "tduyuru_icerik", DuyuruIcerik))
					islemBasarimi = true;
				else
					islemBasarimi = false;
			}
			else
				islemBasarimi = false;
		}

		if (islemBasarimi)
			Session.setAttribute("basarili_islem", warning.getWarnings(3));
		else
			Session.setAttribute("basarisiz_islem", warning.getWarnings(4));
	}

	private void DuyuruBilgisiDuzenle(HttpServletRequest request){
		HttpSession Session = request.getSession();

		String islem = request.getParameter("islem")+"";
		int duyuruID, fakulteID, bolumID, duyuruTurID;
		String duyuruBaslik, duyuruIcerik, baslangicTarih, bitisTarih;

		if (islem.equals("DuyuruBilgisiDuzenle")){
			String secilenDuyuru = request.getParameter("duyuruBelirtec")+"";
			duyuruID = Integer.parseInt(request.getParameter("duyuruID"+secilenDuyuru));

			Session.setAttribute("secilenIslem", "DuyuruIcerik");
			Session.setAttribute("duyuruID", duyuruID);
		}
		if (islem.equals("DuyuruIcerik")){
			Session.setAttribute("secilenIslem", "DuyuruBilgisiDuzenle");
			duyuruID = Integer.parseInt(request.getParameter("duyuruID"));
			fakulteID = Integer.parseInt(request.getParameter("fakulteID"));
			bolumID = Integer.parseInt(request.getParameter("bolumID"));
			duyuruTurID = 1;
			baslangicTarih = request.getParameter("baslangicTarih")+"";
			bitisTarih = request.getParameter("bitisTarih")+"";
			duyuruBaslik = request.getParameter("duyuruBaslik")+"";
			duyuruIcerik = request.getParameter("duyuruIcerik")+"";

			if (isMalformedData(baslangicTarih) | isMalformedData(bitisTarih) | isMalformedData(duyuruBaslik) | isMalformedData(duyuruIcerik)){
				Session.setAttribute("malformed_data", warning.getWarnings(2));
			}
			else{
				tduyuru_icerik DuyuruIcerik = new tduyuru_icerik(duyuruID, duyuruBaslik, duyuruIcerik, baslangicTarih, bitisTarih);
				tduyuru_bilgi DuyuruBilgi = new tduyuru_bilgi(duyuruID, fakulteID, bolumID, duyuruTurID);
				if (controller.DuyuruDuzenle(DuyuruBilgi, "tduyuru_bilgi", null)){
					if (controller.DuyuruDuzenle(null, "tduyuru_icerik", DuyuruIcerik))
						islemBasarimi = true;
					else
						islemBasarimi = false;
				}
				else
					islemBasarimi = false;
			}
			if (islemBasarimi)
				Session.setAttribute("basarili_islem", warning.getWarnings(3));
			else
				Session.setAttribute("basarisiz_islem", warning.getWarnings(4));
		}
	}

	private void KullaniciBilgisiDuzenle(HttpServletRequest request){
		HttpSession Session = request.getSession();

		String islem = request.getParameter("islem");

		if (islem.equals("KullaniciBilgisiDuzenle")){
			islem = request.getParameter("islemBelirtec");
			String SecilenKullanici = request.getParameter("kullaniciBelirtec");
			String username = request.getParameter("username"+SecilenKullanici);

			tuser User;
			tuser_bilgi UserBilgi;

			if (islem.equals("Duzenle")){
				Session.setAttribute("kullanilacakID", username);
				Session.setAttribute("secilenIslem", "KullaniciBilgisi");
			}
			if (islem.equals("Sil")){
				User = new tuser(username, null, 0, 0);
				UserBilgi = new tuser_bilgi(username, null, null, null, null);

				if (controller.KullaniciSil(User, "tuser", null)){
					if (controller.KullaniciSil(null, "tuser_bilgi", UserBilgi))
						islemBasarimi = true;
					else
						islemBasarimi = false;
				}
				else
					islemBasarimi = false;
				if (islemBasarimi)
					Session.setAttribute("basarili_islem", warning.getWarnings(3));
				else
					Session.setAttribute("basarisiz_islem", warning.getWarnings(4));

				if (username.equals((String)(Session.getAttribute("username")+""))){
					Session.setAttribute("sistemdenCikar", "true");
				}
			}
		}
		if (islem.equals("KullaniciBilgisi")){
			Session.setAttribute("secilenIslem", "KullaniciBilgisiDuzenle");
			String username, name, surname, telefon, adres;

			username = request.getParameter("username")+"";
			name = request.getParameter("name")+"";
			surname = request.getParameter("surname")+"";
			telefon = request.getParameter("telefon")+"";
			adres = request.getParameter("adres")+"";

			if (isMalformedData(username) | isMalformedData(name) | isMalformedData(surname) | isMalformedData(telefon) | isMalformedData(adres)){
				Session.setAttribute("malformed_data", warning.getWarnings(2));
			}
			else{
				tuser_bilgi UserBilgi = new tuser_bilgi(username, name, surname, telefon, adres);
				if (controller.KullaniciBilgisiDuzenle(UserBilgi))
					islemBasarimi = true;
				else
					islemBasarimi = false;
			}
			if (islemBasarimi)
				Session.setAttribute("basarili_islem", warning.getWarnings(3));
			else
				Session.setAttribute("basarisiz_islem", warning.getWarnings(4));
		}
	}

	private void KullaniciEkle(HttpServletRequest request){
		HttpSession Session = request.getSession();
		int yetkiID, gorevID;
		String username, password, name, surname, telefon, adres;
		yetkiID = Integer.parseInt(request.getParameter("yetkiID"));
		gorevID = Integer.parseInt(request.getParameter("gorevID"));
		username = request.getParameter("username");
		password = request.getParameter("password");
		name = request.getParameter("name");
		surname = request.getParameter("surname");
		telefon = request.getParameter("telefon");
		adres = request.getParameter("adres");

		if (isMalformedData(username) | isMalformedData(password) | isMalformedData(name) | isMalformedData(surname)
				|isMalformedData(telefon) | isMalformedData(adres) | isMalformedData(yetkiID)){
			Session.setAttribute("malformed_data", warning.getWarnings(2));
		}
		else{
			tuser user = new tuser(username, password, yetkiID, gorevID);
			tuser_bilgi user_bilgi = new tuser_bilgi(username, name, surname, telefon, adres);
			if (controller.KullaniciEkle(user, "tuser", null)){
				if (controller.KullaniciEkle(null, "tuser_bilgi", user_bilgi))
					islemBasarimi = true;
				else
					islemBasarimi = false;
			}
			else
				islemBasarimi = false;
			if (islemBasarimi)
				Session.setAttribute("basarili_islem", warning.getWarnings(3));
			else
				Session.setAttribute("basarisiz_islem", warning.getWarnings(4));
		}
	}

	private boolean isMalformedData(String data){
		data = data.trim();
		if (data.equals(null) | data.equals("") | data.equals(null+"") | data.equals(" "))
			return true;
		return false;
	}

	private boolean isMalformedData(int data){
		if (data == 0){
			return true;
		}
		return false;
	}
}