package com.sti.bootcamp.rekeningdao;

import com.sti.bootcamp.rekeningmodel.AccountModel;
import com.sti.bootcamp.rekeningmodel.CustomerModel;
import com.sti.bootcamp.rekeningmodel.TransactionModel;
import com.sti.bootcamp.rekeningmodel.TransactionTypeModel;
import com.sti.bootcamp.rekeningmodel.WalletAccountModel;
import com.sti.bootcamp.rekeningmodel.WalletModel;

public interface CustomerDao {
	boolean register(CustomerModel customer);
	boolean account(AccountModel account);
	boolean wallet(WalletModel wallet);
	boolean walletAccount(WalletAccountModel walletaccount);
	boolean transaction(TransactionModel transaction);
	boolean transactionType(TransactionTypeModel transaction);
	int getOneCustomer(String username);
//	getDetail
	public void transfer(TransactionModel trans);
	public void topUp(TransactionModel trans);
	public void tarikTunai(TransactionModel trans);
}
