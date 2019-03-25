package com.sti.bootcamp.rekeningmodel;

public class AccountModel {
	private int accountNumber;
	private String accountName;
	private String openDate;
	private int balance;
	private int cif;
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getOpenDate() {
		return openDate;
	}
	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public int getCif() {
		return cif;
	}
	public void setCif(int cif) {
		this.cif = cif;
	}
	
	
}
