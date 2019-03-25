package com.sti.bootcamp.rekeningmodel;

public class TransactionModel {
	private int id;
	private String date;
	private int accountNumberDebit;
	private int accountNumberCredit;
	private int amount;
	private int transactionType;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getAccountNumberDebit() {
		return accountNumberDebit;
	}
	public void setAccountNumberDebit(int accountNumberDebit) {
		this.accountNumberDebit = accountNumberDebit;
	}
	public int getAccountNumberCredit() {
		return accountNumberCredit;
	}
	public void setAccountNumberCredit(int accountNumberCredit) {
		this.accountNumberCredit = accountNumberCredit;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(int transactionType) {
		this.transactionType = transactionType;
	}
	
	
}