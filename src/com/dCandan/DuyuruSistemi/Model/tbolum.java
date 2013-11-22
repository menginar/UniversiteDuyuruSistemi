package com.dCandan.DuyuruSistemi.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbolum")
public class tbolum {
	@Id
	@Column(name="bolumID")
	private int bolumID;
	@Column(name="fakulteID")
	private int fakulteID;
	@Column(name="bolumAd")
	private String bolumAd;
	
	public tbolum(){
		
	}
	
	public tbolum(int bolumID, int fakulteID, String bolumAd){
		this.bolumID = bolumID;
		this.fakulteID = fakulteID;
		this.bolumAd = bolumAd;
	}

	public int getBolumID() {
		return bolumID;
	}

	public void setBolumID(int bolumID) {
		this.bolumID = bolumID;
	}

	public int getFakulteID() {
		return fakulteID;
	}

	public void setFakulteID(int fakulteID) {
		this.fakulteID = fakulteID;
	}

	public String getBolumAd() {
		return bolumAd;
	}

	public void setBolumAd(String bolumAd) {
		this.bolumAd = bolumAd;
	}
}
