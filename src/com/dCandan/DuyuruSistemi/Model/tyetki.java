package com.dCandan.DuyuruSistemi.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tyetki")
public class tyetki {
	@Id
	@Column(name="yetkiID")
	private int yetkiID;
	@Column(name="yetkiAd")
	private String yetkiAd;
	
	public tyetki(){
		
	}
	
	public tyetki(int yetkiID, String yetkiAd){
		this.yetkiID = yetkiID;
		this.yetkiAd = yetkiAd;
	}

	public int getYetkiID() {
		return yetkiID;
	}

	public void setYetkiID(int yetkiID) {
		this.yetkiID = yetkiID;
	}

	public String getYetkiAd() {
		return yetkiAd;
	}

	public void setYetkiAd(String yetkiAd) {
		this.yetkiAd = yetkiAd;
	}
}
