package com.dCandan.DuyuruSistemi.DatabaseController;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;

import com.dCandan.DuyuruSistemi.Model.tduyuru_bilgi;
import com.dCandan.DuyuruSistemi.Model.tduyuru_icerik;

public class database_fakulteYoneticisiController 
									extends database_genelYoneticiController{	

	@SuppressWarnings({ "rawtypes" })
	public int DuyuruIDHesapla(){
		/*
		 *	Bu metod yalnizca duyuru ekleme ve duyuru cogaltma islemleri icin 
		 *	kullanilir.Bu nedenle en az calistirilan metodlardan birisidir. 
		 */
		int duyuruID = 1;//	Hata olussa bile bos donmemek icin default deger atanýyor
		if (Connection.OpenDatabaseConnection()){
			try{
				Query query = (Query) Connection.Session.createQuery("from tduyuru_bilgi");
				List result = query.list();
				Iterator iterator = result.iterator();
				while (iterator.hasNext()){
					tduyuru_bilgi duyuru = (tduyuru_bilgi) iterator.next();
					duyuruID = duyuru.getDuyuruID()+1;
				}
			}catch(Exception e){
				System.out.println("Exception 21 : "+e.getMessage());
			}
			finally{
				Connection.CloseDatabaseConnection();
			}
		}
		return duyuruID;
	}

	public boolean DuyuruEkle(tduyuru_bilgi DuyuruBilgi, String tableName, tduyuru_icerik DuyuruIcerik){
		/*
		 * 	Arguman olarak verilen DuyuruBilgi ve DuyuruIcerik nesneleri 
		 * 	tableName argumani ile belirlenen tablolara eklenir. 
		 */
		boolean isOkey = true;
		if (Connection.OpenDatabaseConnection()){
			try{
				if (tableName.equals("tduyuru_bilgi"))
					Connection.Session.save(DuyuruBilgi);
				if (tableName.equals("tduyuru_icerik"))
					Connection.Session.save(DuyuruIcerik);
				Connection.Session.getTransaction().commit();
			}catch(Exception e){
				isOkey = false;
				System.out.println("Exception 22 : "+e.getMessage());
			}finally{
				Connection.CloseDatabaseConnection();
			}
		}
		return isOkey;
	}
}
