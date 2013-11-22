package com.dCandan.DuyuruSistemi.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tuser_bilgi")
public class tuser_bilgi {
	@Id
	@Column(name="username")
	private String username;
	@Column(name="name")
	private String name;
	@Column(name="surname")
	private String surname;
	@Column(name="telephone")
	private String telephone;
	@Column(name="address")
	private String address;
	
	public tuser_bilgi(){
		
	}
	
	public tuser_bilgi(String username, String name, String surname, String telefon, String adres){
		this.username = username;
		this.name = name;
		this.surname = surname;
		this.telephone = telefon;
		this.address = adres;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
