package com.dCandan.DuyuruSistemi.DatabaseController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;

import com.dCandan.DuyuruSistemi.Model.tbolum;
import com.dCandan.DuyuruSistemi.Model.tduyuru_bilgi;
import com.dCandan.DuyuruSistemi.Model.tduyuru_icerik;
import com.dCandan.DuyuruSistemi.Model.tfakulte;
import com.dCandan.DuyuruSistemi.Model.treklam;
import com.dCandan.DuyuruSistemi.Model.tuser;
import com.dCandan.DuyuruSistemi.Model.tuser_bilgi;

public class database_genelYoneticiController extends database_userController{

	//	------------ LISTELEME BLOGU -------------

	@SuppressWarnings("rawtypes")
	public ArrayList<tfakulte> FakulteListele(){//	(XXX)
		/*
		 * 	Veritabaninda kayitli fakulteleri listelemek isteyen kullanicilar 
		 * 	bu metodu kullanýr.Bu metodun bir handikapi var ki oda butun 
		 * 	fakulteler istense de istenmesede bu metod araciligiyla kullaniciya 
		 * 	gonderilmektedir.
		 */
		ArrayList<tfakulte> fakulteler = new ArrayList<>();
		if (Connection.OpenDatabaseConnection()){
			try{
				Query query = (Query) Connection.Session.createQuery("from tfakulte");
				List result = query.list();
				Iterator iterator = result.iterator();
				while (iterator.hasNext()){
					tfakulte fakulte = (tfakulte) iterator.next();
					fakulteler.add(fakulte);
				}
			}catch(Exception e){
				System.out.println("Exception 7 : "+e.getMessage());
			}
			finally{
				Connection.CloseDatabaseConnection();
			}
		}
		return fakulteler;
	}

	@SuppressWarnings("rawtypes")
	public ArrayList<tbolum> BolumListele(){//	(XXX)
		/*
		 * 	Veritabaninda kayitli bolumleri listelemek isteyen kullanicilar 
		 * 	bu metodu kullanýr.Bu metodun bir handikapi var ki oda butun 
		 * 	bolumler istense de istenmesede bu metod araciligiyla kullaniciya 
		 * 	gonderilmektedir.
		 */
		ArrayList<tbolum> bolumler = new ArrayList<>();
		if (Connection.OpenDatabaseConnection()){
			try{
				Query query = (Query) Connection.Session.createQuery("from tbolum");
				List result = query.list();
				Iterator iterator = result.iterator();
				while (iterator.hasNext()){
					tbolum bolum = (tbolum) iterator.next();
					bolumler.add(bolum);
				}
			}catch(Exception e){
				System.out.println("Exception 8 : "+e.getMessage());
			}
			finally{
				Connection.CloseDatabaseConnection();
			}
		}
		return bolumler;
	}

	@SuppressWarnings("rawtypes")
	public ArrayList<tduyuru_bilgi> DuyuruBilgiListele(){//	(XXX)
		/*
		 * 	Veritabaninda kayitli duyuru bilgilerini listelemek isteyen 
		 * 	kullanicilar bu metodu kullanýr.Bu metodun bir handikapi var ki oda 
		 * 	butun duyuru bilgileri istense de istenmesede bu metod araciligiyla 
		 * 	kullaniciya gonderilmektedir.
		 */
		ArrayList<tduyuru_bilgi> DuyuruBilgiler = new ArrayList<>();
		if (Connection.OpenDatabaseConnection()){
			try{
				Query query = null;
				query = (Query) Connection.Session.createQuery("from tduyuru_bilgi");
				List result = query.list();
				Iterator iterator = result.iterator();
				while (iterator.hasNext()){
					tduyuru_bilgi duyuruBilgi = (tduyuru_bilgi) iterator.next();
					DuyuruBilgiler.add(duyuruBilgi);
				}
			}catch(Exception e){
				System.out.println("Exception 9 : "+e.getMessage());
			}
			finally{
				Connection.CloseDatabaseConnection();
			}
		}
		return DuyuruBilgiler;
	}

	@SuppressWarnings("rawtypes")
	public ArrayList<tduyuru_icerik> DuyuruIcerikListele(){//	(XXX)
		/*
		 * 	Veritabaninda kayitli duyuru iceriklerini listelemek isteyen 
		 * 	kullanicilar bu metodu kullanýr.Bu metodun bir handikapi var ki oda 
		 * 	butun duyuru icerikleri istense de istenmesede bu metod araciligiyla
		 *  kullaniciya gonderilmektedir.
		 */
		ArrayList<tduyuru_icerik> duyuruIcerikler = new ArrayList<>();
		if (Connection.OpenDatabaseConnection()){
			try{
				Query query = (Query) Connection.Session.createQuery("from tduyuru_icerik");
				List result = query.list();
				Iterator iterator = result.iterator();
				while (iterator.hasNext()){
					tduyuru_icerik duyuruIcerik = (tduyuru_icerik) iterator.next();
					duyuruIcerikler.add(duyuruIcerik);
				}
			}catch(Exception e){
				System.out.println("Exception 10 : "+e.getMessage());
			}
			finally{
				Connection.CloseDatabaseConnection();
			}
		}
		return duyuruIcerikler;
	}

	@SuppressWarnings("rawtypes")
	public ArrayList<treklam> ReklamListele(){//	(XXX)
		/*
		 * 	Veritabaninda kayitli reklamlari listelemek isteyen kullanicilar 
		 * 	bu metodu kullanýr.Bu metodun bir handikapi var ki oda butun 
		 * 	reklamlar istense de istenmesede bu metod araciligiyla kullaniciya 
		 * 	gonderilmektedir.
		 */
		ArrayList<treklam> Reklamlar = new ArrayList<>();
		if (Connection.OpenDatabaseConnection()){
			try{
				Query query = (Query) Connection.Session.createQuery("from treklam");
				List result = query.list();
				Iterator iterator = result.iterator();
				while (iterator.hasNext()){
					treklam reklam = (treklam) iterator.next();
					Reklamlar.add(reklam);
				}
			}catch(Exception e){
				System.out.println("Exception 11 : "+e.getMessage());
			}
			finally{
				Connection.CloseDatabaseConnection();
			}
		}
		return Reklamlar;
	}

	@SuppressWarnings("rawtypes")
	public ArrayList<tuser_bilgi> KullaniciBilgiListele(){
		/*
		 * 	Veritabaninda kayitli butun kullanicilar listelenmek istenirse bu 
		 * 	metodu kullanýlýr.
		 */
		ArrayList<tuser_bilgi> Kullanicilar = new ArrayList<>();
		if (Connection.OpenDatabaseConnection()){
			try{
				Query query = (Query) Connection.Session.createQuery("from tuser_bilgi");
				List result = query.list();
				Iterator iterator = result.iterator();
				while (iterator.hasNext()){
					tuser_bilgi UserBilgi = (tuser_bilgi) iterator.next();
					Kullanicilar.add(UserBilgi);
				}
			}catch(Exception e){
				System.out.println("Exception 12 : "+e.getMessage());
			}
			finally{
				Connection.CloseDatabaseConnection();
			}
		}
		return Kullanicilar;
	}
	@SuppressWarnings("rawtypes")
	public ArrayList<tuser> KullaniciListele(){
		/*
		 * 	Veritabaninda kayitli butun kullanicilar listelenmek istenirse bu 
		 * 	metodu kullanýlýr.
		 */
		ArrayList<tuser> Kullanicilar = new ArrayList<>();
		if (Connection.OpenDatabaseConnection()){
			try{
				Query query = (Query) Connection.Session.createQuery("from tuser");
				List result = query.list();
				Iterator iterator = result.iterator();
				while (iterator.hasNext()){
					tuser User = (tuser) iterator.next();
					Kullanicilar.add(User);
				}
			}catch(Exception e){
				System.out.println("Exception 13 : "+e.getMessage());
			}
			finally{
				Connection.CloseDatabaseConnection();
			}
		}
		return Kullanicilar;
	}

	//	------------- DUZENLEME BLOGU -------------

	public boolean ReklamDuzenle(treklam Reklam){
		/*
		 * 	Kayitli olan bir reklam verisi bu metoda arguman olarak verilen 
		 * 	reklam nesnesinin icerigi ile degistirip commit edilir.			 
		 */
		boolean isOkey = true;
		if (Connection.OpenDatabaseConnection()){
			try{
				Connection.Session.update(Reklam);
				Connection.Session.getTransaction().commit();
			}catch(Exception e){
				isOkey = false;
				System.out.println("Exception 14 : "+e.getMessage());
			}
			finally{
				Connection.CloseDatabaseConnection();
			}
		}
		return isOkey;
	}

	public boolean DuyuruDuzenle(tduyuru_bilgi DuyuruBilgi, String tableName, tduyuru_icerik DuyuruIcerik){
		/*
		 *  Kayitli olan duyuru bilgi ve duyuru icerik verileri bu metoda 
		 *  arguman olarak verilen DuyuruBilgi ve DuyuruIcerik nesneleriyle 
		 *  tableName argumanindan yararlanarak degistirilir.
		 */
		boolean isOkey = true;
		if (Connection.OpenDatabaseConnection()){
			try{
				if (tableName.equals("tduyuru_bilgi"))
					Connection.Session.update(DuyuruBilgi);
				if (tableName.equals("tduyuru_icerik"))
					Connection.Session.update(DuyuruIcerik);
				Connection.Session.getTransaction().commit();
			}catch(Exception e){
				isOkey = false;
				System.out.println("Exception 15 : "+e.getMessage());
			}
			finally{
				Connection.CloseDatabaseConnection();
			}
		}
		return isOkey;
	}

	public boolean KullaniciBilgisiDuzenle(tuser_bilgi UserBilgi){
		/*
		 * 	Kullanicinin duzenlenebilen bilgileri(parola haric) tuser_bilgi 
		 * 	tablosunda tutuldugu icin genel yonetici tarafýndan yalnizca bu 
		 * 	tabloda guncelleme yapilabilir.Arguman olarak gelen veri ile bu 
		 * 	tablodaki veri eslestirilir ve gerekli guncellemeler yapilir.
		 */
		boolean isOkey = true;
		if (Connection.OpenDatabaseConnection()){
			try{
				Connection.Session.update(UserBilgi);
				Connection.Session.getTransaction().commit();
			}catch(Exception e){
				isOkey = false;
				System.out.println("Exception 16 : "+e.getMessage());
			}
			finally{
				Connection.CloseDatabaseConnection();
			}
		}
		return isOkey;
	}

	//	-------------- SILME BLOGU ----------------

	public boolean ReklamSil(treklam Reklam){
		/*
		 * 	Belirlenen reklami silme islemini yapan metoddur.
		 */
		boolean isOkey = true;
		if (Connection.OpenDatabaseConnection()){
			try{
				Connection.Session.delete(Reklam);
				Connection.Session.getTransaction().commit();
			}catch(Exception e){
				isOkey = false;
				System.out.println("Exception 17 : "+e.getMessage());
			}
			finally{
				Connection.CloseDatabaseConnection();
			}
		}
		return isOkey;
	}

	public boolean DuyuruSil(tduyuru_bilgi DuyuruBilgi, String tableName, tduyuru_icerik DuyuruIcerik){
		/*
		 * 	Arguman olarak verilen DuyuruBilgi ve DuyuruIcerik nesnelerinin 
		 * 	veritabanindaki karsilikleri tableName argumani yardimiyla bulunur
		 *  ve silinir.
		 */
		boolean isOkey = true;
		if (Connection.OpenDatabaseConnection()){
			try{
				if (tableName.equals("tduyuru_bilgi"))
					Connection.Session.delete(DuyuruBilgi);
				if (tableName.equals("tduyuru_icerik"))
					Connection.Session.delete(DuyuruIcerik);
				Connection.Session.getTransaction().commit();
			}catch(Exception e){
				isOkey = false;
				System.out.println("Exception 18 : "+e.getMessage());
			}
			finally{
				Connection.CloseDatabaseConnection();
			}
		}
		return isOkey;
	}

	public boolean KullaniciSil(tuser User, String tableName, tuser_bilgi UserBilgi){
		/*
		 * 	Arguman olarak verilen User ve UserBilgi nesnelerinin 
		 * 	veritabanindaki karsilikleri  tableName argumani yardimiyla bulunur 
		 * 	ve silinir.
		 */
		boolean isOkey = true;
		if (Connection.OpenDatabaseConnection()){
			try{
				if (tableName.equals("tuser"))
					Connection.Session.delete(User);
				if (tableName.equals("tuser_bilgi"))
					Connection.Session.delete(UserBilgi);
				Connection.Session.getTransaction().commit();
			}catch(Exception e){
				isOkey = false;
				System.out.println("Exception 19 : "+e.getMessage());
			}
			finally{
				Connection.CloseDatabaseConnection();
			}
		}
		return isOkey;
	}

	//	--------------	EKLEME BLOGU  ------------------

	public boolean KullaniciEkle(tuser User, String tableName, tuser_bilgi UserBilgi){
		boolean isOkey = true;
		if (Connection.OpenDatabaseConnection()){
			try{
				if (tableName.equals("tuser"))
					Connection.Session.save(User);
				if (tableName.equals("tuser_bilgi"))
					Connection.Session.save(UserBilgi);
				Connection.Session.getTransaction().commit();
			}catch(Exception e){
				isOkey = false;
				System.out.println("Exception 20 : "+e.getMessage());
			}finally{
				Connection.CloseDatabaseConnection();
			}
		}
		return isOkey;
	}
}
