package com.dCandan.DuyuruSistemi.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tduyuru_tur")
public class tduyuru_tur {
	@Id
	@Column(name="duyuruTurID")
	private int duyuruTurID;
	@Column(name="duyuruTurAd")
	private String duyuruTurAd;
	
	public tduyuru_tur(){
		
	}
	
	public tduyuru_tur(int duyuruTurID, String duyuruTurAd){
		this.duyuruTurID = duyuruTurID;
		this.duyuruTurAd = duyuruTurAd;
	}

	public int getDuyuruTurID() {
		return duyuruTurID;
	}

	public void setDuyuruTurID(int duyuruTurID) {
		this.duyuruTurID = duyuruTurID;
	}

	public String getDuyuruTurAd() {
		return duyuruTurAd;
	}

	public void setDuyuruTurAd(String duyuruTurAd) {
		this.duyuruTurAd = duyuruTurAd;
	}
}
