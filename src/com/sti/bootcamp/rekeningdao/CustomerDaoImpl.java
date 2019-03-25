package com.sti.bootcamp.rekeningdao;


import com.sti.bootcamp.rekeningdb.DBConnection;
import com.sti.bootcamp.rekeningdb.DBUtil;
import com.sti.bootcamp.rekeningmodel.AccountModel;
import com.sti.bootcamp.rekeningmodel.CustomerModel;
import com.sti.bootcamp.rekeningmodel.TransactionModel;
import com.sti.bootcamp.rekeningmodel.TransactionTypeModel;
import com.sti.bootcamp.rekeningmodel.WalletAccountModel;
import com.sti.bootcamp.rekeningmodel.WalletModel;

public class CustomerDaoImpl implements CustomerDao{
	
	DBConnection konek = new DBConnection();

	DBUtil util = new DBUtil(konek.getConnection());
	
	@Override
	public boolean register(CustomerModel customer) {
		return util.register(customer);
	}
	
	
	@Override
	public boolean account(AccountModel account) {
//		int id = account.getCif();
//		account.
		return util.account(account);
	}

	@Override
	public boolean wallet(WalletModel wallet) {
		
		return util.wallet(wallet);
	}

	@Override
	public boolean walletAccount(WalletAccountModel walletaccount) {
		// TODO Auto-generated method stub
		return util.walletAccount(walletaccount);
	}

	@Override
	public boolean transaction(TransactionModel transaction) {
		return util.transaction(transaction);
	}

	@Override
	public int getOneCustomer(String username) {
		// TODO Auto-generated method stub
		return util.getOneCustomer();
	}


	@Override
	public void transfer(TransactionModel trans) {
		util.transfer(trans);
	}


	@Override
	public void topUp(TransactionModel trans) {
		util.topUp(trans);
	}


	@Override
	public void tarikTunai(TransactionModel trans) {
		util.tarikTunai(trans);
	}


	@Override
	public boolean transactionType(TransactionTypeModel transaction) {
		// TODO Auto-generated method stub
		return false;
	}
	}


	