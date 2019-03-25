package com.sti.bootcamp.rekeningdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.sti.bootcamp.rekeningmodel.AccountModel;
import com.sti.bootcamp.rekeningmodel.CustomerModel;
import com.sti.bootcamp.rekeningmodel.TransactionModel;
import com.sti.bootcamp.rekeningmodel.TransactionTypeModel;
import com.sti.bootcamp.rekeningmodel.WalletAccountModel;
import com.sti.bootcamp.rekeningmodel.WalletModel;

public class DBUtil {
	static Connection mysqlkonek;
	static Statement stmt;
	static ResultSet rs;
	static PreparedStatement preparedStatement;

	// buat object modelnya
	CustomerModel customer = new CustomerModel();
	AccountModel account = new AccountModel();
	TransactionModel transaksi = new TransactionModel();
	WalletModel wallet = new WalletModel();
	WalletAccountModel walletacoount = new WalletAccountModel();
	TransactionModel transaction = new TransactionModel();
	TransactionTypeModel transactiontype = new TransactionTypeModel();

	// buat koneksi driver
	public DBUtil(Connection connection) {
		mysqlkonek = connection;
	}

	// add data customer
	public boolean register(CustomerModel customer) {
		try {
			String sql = "INSERT INTO tb_customer VALUE( NULL, '" + customer.getFirstName() + "'," + "'"
					+ customer.getLastName() + "','" + customer.getBirthDate() + "','" + customer.getUsername() + "','"
					+ customer.getPassword() + "',null)";
			System.out.println(sql);
			stmt = mysqlkonek.createStatement();
			stmt.execute(sql);
			return true;
		} catch (Exception e) {
			e.getMessage();
		}

		return false;
	}

	public int getOneCustomer() {
		int cif = 0;
		try {
			String sql = "SELECT cif FROM tb_customer order by cif desc limit 1";
			preparedStatement = mysqlkonek.prepareStatement(sql);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				cif = rs.getInt("cif");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cif;
	}

	public boolean account(AccountModel account) {
		try {
			int cif = getOneCustomer();
			String sql = "INSERT INTO  tb_account( accountName, cif, balance) VALUE(?,?,?)";
			preparedStatement = mysqlkonek.prepareStatement(sql);
			// preparedStatement.setInt(account.getAccountNumber(),);
			preparedStatement.setString(1, account.getAccountName());
			preparedStatement.setInt(2, cif);
			preparedStatement.setInt(3, account.getBalance());
			preparedStatement.execute();
			return true;
		} catch (Exception e) {
			e.getMessage();
		}
		return false;

	}

	public boolean wallet(WalletModel wallet) {
		try {
			String sql = "INSERT INTO tb_wallet (description) VALUE('" + wallet.getDescription() + "')";
			stmt = mysqlkonek.createStatement();
			stmt.execute(sql);
			return true;
		} catch (Exception e) {
			e.getMessage();
		}
		return false;

	}

	public boolean walletAccount(WalletAccountModel walletaccount) {
		try {
			String sql = "INSERT INTO tb_walletaccount (walletId, accountNumber) VALUE ('" + walletaccount.getWalletId()
					+ "','" + walletaccount.getAccountNumber() + "')";
			stmt = mysqlkonek.createStatement();
			stmt.execute(sql);
			return true;
		} catch (Exception e) {
			e.getMessage();
		}
		return false;
	}

	public boolean transaction(TransactionModel transaction) {
		try {
			String sql = "INSERT INTO tb_transaction(accountNumberDebit, accountNumberCredit, amount, transactionType) VALUE(?,?,?,?)";
			preparedStatement = mysqlkonek.prepareStatement(sql);
			preparedStatement.setInt(1, transaction.getAccountNumberDebit());
			preparedStatement.setInt(2, transaction.getAccountNumberCredit());
			preparedStatement.setInt(3, transaction.getAmount());
			preparedStatement.setInt(4, transaction.getTransactionType());
			preparedStatement.execute();
			return true;
		} catch (Exception e) {
			e.getMessage();
		}
		return false;
	}

//	public boolean TransactionType(TransactionTypeModel transactionType) {
//		try {
//			String sql = "INSERT INTO transactionType (code,description) VALUE(?,?)";
//			preparedStatement = mysqlkonek.prepareStatement(sql);
//			preparedStatement.setString(1, getCode());
//			preparedStatement.setString(2, transactionType.getDescription());
//			preparedStatement.execute();
//			return true;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return false;
//	}

	public void transfer(TransactionModel trans) {
		try {
			String sql = " SELECT accountnumber FROM tb_account WHERE accountNumber =? ";
			preparedStatement = mysqlkonek.prepareStatement(sql);
			preparedStatement.setInt(1, transaction.getAccountNumberCredit());
			rs = preparedStatement.executeQuery();

			if (rs.getRow() > -1) {
				int balance = getBalance(trans.getAccountNumberDebit());
				int balance2 = getBalance(trans.getAccountNumberCredit());
				sql = "INSERT INTO tb_transaction ( accountNumberDebit, accountNumberCredit, amount, transactionType) VALUE (?,?,?,?)";
				preparedStatement = mysqlkonek.prepareStatement(sql);
				preparedStatement.setInt(1, trans.getAccountNumberDebit());
				preparedStatement.setInt(2, trans.getAccountNumberCredit());
				preparedStatement.setInt(3, trans.getAmount());
				preparedStatement.setInt(4, trans.getTransactionType());

				preparedStatement.execute();
				updateBalance(trans.getAccountNumberDebit(), balance + trans.getAmount());
				updateBalance(trans.getAccountNumberCredit(), balance2 - trans.getAmount());

			}
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void topUp(TransactionModel transaksi) {
		try {
			String sql = " SELECT accountNumber FROM tb_account WHERE accountNumber =? ";
			preparedStatement = mysqlkonek.prepareStatement(sql);
			preparedStatement.setInt(1, transaksi.getAccountNumberDebit());
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.getRow() > -1) {
				int balance = getBalance(transaksi.getAccountNumberDebit());
				sql = "INSERT INTO tb_transaction ( accountNumberDebit, accountNumberCredit, amount, transactionType) VALUE (?,?,?,?)";
				preparedStatement = mysqlkonek.prepareStatement(sql);
				preparedStatement.setInt(1, transaksi.getAccountNumberDebit());
				preparedStatement.setInt(2, 0);
				preparedStatement.setInt(3, transaksi.getAmount());
				preparedStatement.setInt(4, transaksi.getTransactionType());

				preparedStatement.execute();
				updateBalance(transaksi.getAccountNumberDebit(), balance + transaksi.getAmount());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Tarik Tunai
	public void tarikTunai(TransactionModel transaksi) {
		try {
			String sql = " SELECT accountnumber FROM tb_account WHERE accountNumber =? ";
			preparedStatement = mysqlkonek.prepareStatement(sql);
			preparedStatement.setInt(1, transaksi.getAccountNumberCredit());
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.getRow() > -1) {

				int balance3 = getBalance(transaksi.getAccountNumberCredit());
				System.out.println(balance3);
				sql = "INSERT INTO tb_transaction ( accountNumberDebit, accountNumberCredit, amount, transactionType) VALUE (?,?,?,?)";
				preparedStatement = mysqlkonek.prepareStatement(sql);
				preparedStatement.setInt(1, 0);
				preparedStatement.setInt(2, transaksi.getAccountNumberCredit());
				preparedStatement.setInt(3, transaksi.getAmount());
				preparedStatement.setInt(4, transaksi.getTransactionType());

				preparedStatement.execute();
				updateBalance(transaksi.getAccountNumberCredit(), balance3 - transaksi.getAmount());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getBalance(int accountNumber) {
		int balance = 0;
		try {
			String sql = "SELECT balance FROM tb_account WHERE accountNumber = '" + accountNumber + "'";
//				System.out.println(sql);
			preparedStatement = mysqlkonek.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				balance = rs.getInt("balance");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return balance;
	}

	public void updateBalance(int accountNumber, int balance) {
		try {
			String sql = "UPDATE tb_account set balance = ? WHERE accountNumber = ? ";
			preparedStatement = mysqlkonek.prepareStatement(sql);
			preparedStatement.setInt(1, balance);
			preparedStatement.setInt(2, accountNumber);
			preparedStatement.execute();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

}
