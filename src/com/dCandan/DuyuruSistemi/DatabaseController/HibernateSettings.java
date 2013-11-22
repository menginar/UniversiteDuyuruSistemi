package com.dCandan.DuyuruSistemi.DatabaseController;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSettings {
	Session Session = null;
	SessionFactory SessionFactory = null;
	
	public HibernateSettings(){
		OpenDatabaseConnection();
	}
	
	@SuppressWarnings("deprecation")
	public boolean OpenDatabaseConnection(){
		try{ 
			SessionFactory = new Configuration().configure().buildSessionFactory();
			Session = SessionFactory.openSession();
			Session.beginTransaction();
		}catch (HibernateException e){
			System.out.println("Exception 1 : "+e.getMessage());
			return false;
		}
		return true;
	}

	public void CloseDatabaseConnection(){
		Session.close();
		SessionFactory.close();
	}
}
