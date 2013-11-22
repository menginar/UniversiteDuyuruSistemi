package com.dCandan.DuyuruSistemi.DatabaseController;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;

import com.dCandan.DuyuruSistemi.Model.tbolum;
import com.dCandan.DuyuruSistemi.Model.tfakulte;
import com.dCandan.DuyuruSistemi.Model.tuser;
import com.dCandan.DuyuruSistemi.Model.tuser_bilgi;
import com.dCandan.DuyuruSistemi.Model.tyetki;

public class database_userController {
	/* 
	 * (Performans Iyilestirmesi)
	 * 	Her islem icin bir baglanti acilacak. Islem bittigi anda kapatilacaktir.
	 * 	Bu amacta her islem oncesinde veritabani ile baglanti kurabilmek icin 
	 * 	ayar yapan HibernateSettings sinifindan Connection nesnesi yaratildi. 
	 * 	Ve bu nesne ile her islem öncesinde veritabani ile baglanti kurulup 
	 * 	islem yapilacak ve islem bittiginde baglanti kapatilacaktir. 
	 * 	Baglanti kurulamaz ise islem bloguna hic girilmeyecek ve fazladan 
	 * 	kod calistirilmayacaktir.
	 */
	HibernateSettings Connection;

	public database_userController(){
		Connection = new HibernateSettings();
	}

	@SuppressWarnings("rawtypes")
	public void isUser(tuser user){
		/*	
		 * 	Bu blokta kullanicidan alinan username ve password ile uye olup 
		 * 	olunmadigi kontrol edilir.
		 */
		if (Connection.OpenDatabaseConnection()){
			try{
				Query query = (Query) Connection.Session.createQuery("from tuser");
				List result = query.list();
				Iterator iterator = result.iterator();

				while (iterator.hasNext()){
					tuser userData = (tuser) iterator.next();
					if (user.getUsername().equals(userData.getUsername())){
						if (user.getPassword().equals(userData.getPassword())){
							user.setYetkiID(userData.getYetkiID());
							user.setGorevID(userData.getGorevID());
						}
					}
				}
			}catch(Exception e){
				System.out.println("Exception 2 : "+e.getMessage());
			}
			finally{
				Connection.CloseDatabaseConnection();
			}
		}
	}

	@SuppressWarnings("rawtypes")
	public String IsimGetir(String tabloAdi, String ID){
		/*
		 * 	Bu blokta arguman olarak istenen tabloda yine bir argüman olan 
		 * 	id verisine karsilik gelen satir okunur ve geri dondurulur.
		 */
		String data = "Ýsim Yok";//	Bos veri donulmemesi icin default deger ataniyor.
		if (Connection.OpenDatabaseConnection()){
			try{
				Query query = null;
				if (tabloAdi.equals("tuser_bilgi")){
					query = (Query) Connection.Session.createQuery("from tuser_bilgi");
					List result = query.list();
					Iterator iterator = result.iterator();
					while (iterator.hasNext()){
						tuser_bilgi userData = (tuser_bilgi) iterator.next();
						if (userData.getUsername().equals(ID)){
							data = userData.getName()+" "+userData.getSurname();
							break;
						}
					}
				}
				if (tabloAdi.equals("tbolum")){
					query = (Query) Connection.Session.createQuery("from tbolum");
					List result = query.list();
					Iterator iterator = result.iterator();
					while (iterator.hasNext()){
						tbolum bolum = (tbolum) iterator.next();
						if (bolum.getBolumID() == Integer.parseInt(ID)){
							data = bolum.getBolumAd();
							break;
						}
					}
				}
				if (tabloAdi.equals("tfakulte")){
					query = (Query) Connection.Session.createQuery("from tfakulte");
					List result = query.list();
					Iterator iterator = result.iterator();
					while (iterator.hasNext()){
						tfakulte fakulte = (tfakulte) iterator.next();
						if (fakulte.getFakulteID() == Integer.parseInt(ID)){
							data = fakulte.getFakulteAd();
							break;
						}
					}
				}
				if (tabloAdi.equals("tyetki")){
					query = (Query) Connection.Session.createQuery("from tyetki");
					List result = query.list();
					Iterator iterator = result.iterator();
					while (iterator.hasNext()){
						tyetki yetki = (tyetki) iterator.next();
						if (yetki.getYetkiID() == Integer.parseInt(ID)){
							data = yetki.getYetkiAd();
							break;
						}
					}
				}
			}catch(Exception e){
				System.out.println("Exception 3 : "+e.getMessage());
			}
			finally{
				Connection.CloseDatabaseConnection();
			}
		}
		return data;
	}

	public boolean SifreDegis(tuser user){
		/*
		 * 	Kullanicinin sifre degisme istegine yanit verebilmek icin bu metod 
		 * 	olusturulmustur.Kullanicidan alinan bilgiler ile olusturulan user 
		 * 	nesnesi kullanilarak guncelleme yapilir.
		 */
		boolean isOkey = true;
		if (Connection.OpenDatabaseConnection()){
			try{
				Connection.Session.update(user);
				Connection.Session.getTransaction().commit();
			}catch(Exception e){
				isOkey = false;
				System.out.println("Exception 4 : "+e.getMessage());
			}
			finally{
				Connection.CloseDatabaseConnection();
			}
		}
		return isOkey;
	}
}
