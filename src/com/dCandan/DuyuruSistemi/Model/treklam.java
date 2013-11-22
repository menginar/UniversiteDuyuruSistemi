package com.dCandan.DuyuruSistemi.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="treklam")
public class treklam {
	@Id
	@Column(name="reklamID")
	private int reklamID;
	@Column(name="reklamIcerik")
	private String reklamIcerik;
	@Column(name="reklamResimPath")
	private String reklamResimPath;
	@Column(name="reklamBaslangicTarih")
	private String reklamBaslangicTarih;
	@Column(name="reklamBitisTarih")
	private String reklamBitisTarih;
	@Column(name="username")
	private String username;
	@Column(name="yayinlanmaDurum")
	private int yayinlanmaDurum;
	
	public treklam(){
		
	}
	
	public treklam(int reklamID, String reklamIcerik, String reklamBaslangic, String reklamBitis, String reklamResimPath, String username, int yayinlanmaDurum){
		this.reklamID = reklamID;
		this.reklamIcerik = reklamIcerik;
		this.reklamBaslangicTarih = reklamBaslangic;
		this.reklamBitisTarih = reklamBitis;
		this.reklamResimPath = reklamResimPath;
		this.username = username;
		this.yayinlanmaDurum = yayinlanmaDurum;
	}

	public int getReklamID() {
		return reklamID;
	}

	public void setReklamID(int reklamID) {
		this.reklamID = reklamID;
	}

	public String getReklamIcerik() {
		return reklamIcerik;
	}

	public void setReklamIcerik(String reklamIcerik) {
		this.reklamIcerik = reklamIcerik;
	}

	public String getReklamResimPath() {
		return reklamResimPath;
	}

	public void setReklamResimPath(String reklamResimPath) {
		this.reklamResimPath = reklamResimPath;
	}

	public String getReklamBaslangicTarih() {
		return reklamBaslangicTarih;
	}

	public void setReklamBaslangicTarih(String reklamBaslangicTarih) {
		this.reklamBaslangicTarih = reklamBaslangicTarih;
	}

	public String getReklamBitisTarih() {
		return reklamBitisTarih;
	}

	public void setReklamBitisTarih(String reklamBitisTarih) {
		this.reklamBitisTarih = reklamBitisTarih;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getYayinlanmaDurum() {
		return yayinlanmaDurum;
	}

	public void setYayinlanmaDurum(int yayinlanmaDurum) {
		this.yayinlanmaDurum = yayinlanmaDurum;
	}
}
