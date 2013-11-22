package com.dCandan.DuyuruSistemi.DatabaseController;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;

import com.dCandan.DuyuruSistemi.Model.treklam;

public class database_reklamYoneticisiController 
									extends database_genelYoneticiController {
	@SuppressWarnings("rawtypes")
	public int reklamIDHesapla(){
		/*
		 * 	Bu metod yalnizca reklam ekleme ve reklam duzenleme islemleri icin 
		 * 	kullanilir.Bu nedenle en az kullanilan metodlardan birisidir.
		 */
		int reklamID = 1;//	Bos donmemek icin default deger atamasi
		if (Connection.OpenDatabaseConnection()){
			try{
				Query query = (Query) Connection.Session.createQuery("from treklam");
				List result = query.list();
				Iterator iterator = result.iterator();
				while (iterator.hasNext()){
					treklam reklam = (treklam) iterator.next();
					reklamID = reklam.getReklamID()+1;
				}
			}catch(Exception e){
				System.out.println("Exception 5 : "+e.getMessage());
			}
			finally{
				Connection.CloseDatabaseConnection();	
			}
		}
		return reklamID;
	}

	public boolean ReklamEkle(treklam Reklam){
		/*
		 * 	Arguman olarak girilen Reklam nesnesi veritabanindaki belirli 
		 * 	tabloya kaydedilir.
		 */
		boolean isOkey = true;
		if (Connection.OpenDatabaseConnection()){
			try{
				Connection.Session.save(Reklam);
				Connection.Session.getTransaction().commit();
			}catch(Exception e){
				isOkey = false;
				System.out.println("Exception 6 : "+e.getMessage());
			}
			finally{
				Connection.CloseDatabaseConnection();
			}
		}
		return isOkey;
	}
}
