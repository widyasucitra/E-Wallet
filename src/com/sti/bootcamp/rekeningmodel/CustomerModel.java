package com.sti.bootcamp.rekeningmodel;

public class CustomerModel {
	private int cif, primaryAccount;
	private String firstName, lastName, birthDate, username, password;
	public int getCif() {
		return cif;
	}
	public void setCif(int cif) {
		this.cif = cif;
	}
	public int getPrimaryAccount() {
		return primaryAccount;
	}
	public void setPrimaryAccount(int primaryAccount) {
		this.primaryAccount = primaryAccount;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
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
	
}
