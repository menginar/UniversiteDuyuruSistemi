package com.dCandan.DuyuruSistemi.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tuser")
public class tuser {
	@Id
	@Column(name="username")
	private String username;
	@Column(name="password")
	private String password;
	@Column(name="yetkiID")
	private int yetkiID;
	@Column(name="gorevID")
	private int gorevID;
	
	public tuser(){
		
	}
	
	public tuser(String username, String password, int yetkiID, int gorevID){
		this.username = username;
		this.password = password;
		this.yetkiID = yetkiID;
		this.gorevID = gorevID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getYetkiID() {
		return yetkiID;
	}

	public void setYetkiID(int yetkiID) {
		this.yetkiID = yetkiID;
	}

	public int getGorevID() {
		return gorevID;
	}

	public void setGorevID(int gorevID) {
		this.gorevID = gorevID;
	}
}
