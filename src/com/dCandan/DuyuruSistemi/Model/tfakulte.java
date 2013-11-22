package com.dCandan.DuyuruSistemi.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tfakulte")
public class tfakulte {
	@Id
	@Column(name="fakulteID")
	private int fakulteID;
	@Column(name="fakulteAd")
	private String fakulteAd;
	
	public tfakulte(){
		
	}
	
	public tfakulte(int fakulteID, String fakulteAd){
		this.fakulteID = fakulteID;
		this.fakulteAd = fakulteAd;
	}

	public int getFakulteID() {
		return fakulteID;
	}

	public void setFakulteID(int fakulteID) {
		this.fakulteID = fakulteID;
	}

	public String getFakulteAd() {
		return fakulteAd;
	}

	public void setFakulteAd(String fakulteAd) {
		this.fakulteAd = fakulteAd;
	}
}
