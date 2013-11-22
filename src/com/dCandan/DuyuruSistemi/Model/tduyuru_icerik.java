package com.dCandan.DuyuruSistemi.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tduyuru_icerik")
public class tduyuru_icerik {
	@Id
	@Column(name="duyuruID")
	private int duyuruID;
	@Column(name="duyuruBaslik")
	private String duyuruBaslik;
	@Column(name="duyuruIcerik")
	private String duyuruIcerik;
	@Column(name="duyuruBaslangicTarih")
	private String duyuruBaslangicTarih;
	@Column(name="duyuruBitisTarih")
	private String duyuruBitisTarih;
	
	public tduyuru_icerik(){
		
	}
	
	public tduyuru_icerik(int duyuruID, String duyuruBaslik, String duyuruIcerik, String duyuruBaslangic, String duyuruBitis){
		this.duyuruID = duyuruID;
		this.duyuruBaslik = duyuruBaslik;
		this.duyuruIcerik = duyuruIcerik;
		this.duyuruBaslangicTarih = duyuruBaslangic;
		this.duyuruBitisTarih = duyuruBitis;
	}
	
	public int getDuyuruID() {
		return duyuruID;
	}

	public void setDuyuruID(int duyuruID) {
		this.duyuruID = duyuruID;
	}

	public String getDuyuruBaslik() {
		return duyuruBaslik;
	}

	public void setDuyuruBaslik(String duyuruBaslik) {
		this.duyuruBaslik = duyuruBaslik;
	}

	public String getDuyuruIcerik() {
		return duyuruIcerik;
	}

	public void setDuyuruIcerik(String duyuruIcerik) {
		this.duyuruIcerik = duyuruIcerik;
	}

	public String getDuyuruBaslangicTarih() {
		return duyuruBaslangicTarih;
	}

	public void setDuyuruBaslangicTarih(String duyuruBaslangicTarih) {
		this.duyuruBaslangicTarih = duyuruBaslangicTarih;
	}

	public String getDuyuruBitisTarih() {
		return duyuruBitisTarih;
	}

	public void setDuyuruBitisTarih(String duyuruBitisTarih) {
		this.duyuruBitisTarih = duyuruBitisTarih;
	}
}
