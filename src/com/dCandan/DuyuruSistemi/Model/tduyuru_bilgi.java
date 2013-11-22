package com.dCandan.DuyuruSistemi.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tduyuru_bilgi")
public class tduyuru_bilgi {
	@Id
	@Column(name="duyuruID")
	private int duyuruID;
	@Column(name="fakulteID")
	private int fakulteID;
	@Column(name="bolumID")
	private int bolumID;
	@Column(name="duyuruTurID")
	private int duyuruTurID;
	
	public tduyuru_bilgi(){
		
	}
	
	public tduyuru_bilgi(int duyuruID, int fakulteID, int bolumID, int duyuruTurID){
		this.duyuruID = duyuruID;
		this.fakulteID = fakulteID;
		this.bolumID = bolumID;
		this.duyuruTurID = duyuruTurID;
	}

	public int getDuyuruID() {
		return duyuruID;
	}

	public void setDuyuruID(int duyuruID) {
		this.duyuruID = duyuruID;
	}

	public int getFakulteID() {
		return fakulteID;
	}

	public void setFakulteID(int fakulteID) {
		this.fakulteID = fakulteID;
	}

	public int getBolumID() {
		return bolumID;
	}

	public void setBolumID(int bolumID) {
		this.bolumID = bolumID;
	}

	public int getDuyuruTurID() {
		return duyuruTurID;
	}

	public void setDuyuruTurID(int duyuruTurID) {
		this.duyuruTurID = duyuruTurID;
	}
}
