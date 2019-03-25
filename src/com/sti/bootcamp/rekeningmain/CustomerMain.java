package com.sti.bootcamp.rekeningmain;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

import com.sti.bootcamp.rekeningdao.CustomerDao;
import com.sti.bootcamp.rekeningdao.CustomerDaoImpl;
import com.sti.bootcamp.rekeningmodel.AccountModel;
import com.sti.bootcamp.rekeningmodel.CustomerModel;
import com.sti.bootcamp.rekeningmodel.TransactionModel;
import com.sti.bootcamp.rekeningmodel.TransactionTypeModel;
import com.sti.bootcamp.rekeningmodel.WalletAccountModel;
import com.sti.bootcamp.rekeningmodel.WalletModel;

public class CustomerMain {
//	static InputStreamReader inputStreamReader = new InputStreamReader(System.in);

//	static BufferedReader input = new BufferedReader(inputStreamReader);
	static Scanner key = new java.util.Scanner(System.in);
	static CustomerDao customerDAO = new CustomerDaoImpl();
	static CustomerModel customer;

	static String username;

	public static void menu() {
		System.out.println("========== Main Menu ==============");
		System.out.println("1. Registration:");
		System.out.println("2. Login:");
		System.out.println("0. Exit :");
		System.out.println("Pilihan  :");
		try {
			String pilihan = key.nextLine();
			if (pilihan.equals("0")) {
				System.exit(0);
			} else if (pilihan.equals("1")) {
				register();
				account();
				System.out.println("=============== Wallet =================");
				System.out.println("1. Create Wallet:");
				System.out.println("99. Back To Main Menu :");
				String pilih = key.nextLine();
				if (pilih.equals("")) {
					System.exit(0);
				} else if (pilih.equals("1")) {

					wallet();

				}
//			} else if (pilihan.equals("2")) {
//			login()
			} else {
				System.out.println("Wrong Input");
				menu();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
//		if (DBConnection.getConnection()!= null) {
//			System.out.println("berhasil");
//		} else {
//			System.out.println("tidak berhasil");
//			
//		}
		menu();
		menu1();
		transaction();
//		System.out.println("hhghhhh");
	}

	private static void register() {
		CustomerModel regiscustomer = new CustomerModel();
		try {
			System.out.println("=========== Register ===============");
			System.out.println("Input First Name:");
			regiscustomer.setFirstName(key.nextLine().trim());
			System.out.println("Input Last Name: ");
			regiscustomer.setLastName(key.nextLine().trim());
			System.out.println("Input BirthDate");
			regiscustomer.setBirthDate(key.nextLine().trim());
			System.out.println("Username");
			regiscustomer.setUsername(key.nextLine().trim());
			username = regiscustomer.getUsername();
			System.out.println("Password");
			regiscustomer.setPassword(key.nextLine().trim());
			customerDAO.register(regiscustomer);
			if (customerDAO.register(regiscustomer)) {
				System.out.println("Success");
			} else {
				System.out.println("Failed");
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	static void account() {// int cif)
		AccountModel akun = new AccountModel();
		try {
			System.out.println("=============== Account ===================");
			System.out.println("Input Account Number");
			akun.setAccountNumber(Integer.parseInt(key.nextLine()));
			System.out.println("Input Account Name");
			akun.setAccountName(key.nextLine().trim());
			System.out.println("Balance");
			akun.setBalance(Integer.parseInt(key.nextLine()));
			customerDAO.account(akun);
			if (customerDAO.account(akun)) {
				System.out.println("Success");
			} else {
				System.out.println("Failed");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void wallet() {
		WalletModel daftarWallet = new WalletModel();
		try {
			System.out.println("============= Wallet =============");
//			daftarWallet.setId(Integer.parseInt(key.nextLine().trim()));
			System.out.println("Description");
			daftarWallet.setDescription(key.nextLine().trim());
//			System.out.println("Masukan Created Date");
//			daftarWallet.setCreatedDate(key.nextLine().trim());
			if (customerDAO.wallet(daftarWallet)) {
				System.out.println("Success");
			} else {
				System.out.println("Failed");
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	static void walletAccount() {
		WalletAccountModel walletAccount = new WalletAccountModel();
		try {
			System.out.println("========== Wallet Account ============");
			System.out.println("Input  WalletId:");
			walletAccount.setWalletId(Integer.parseInt(key.nextLine().trim()));
			System.out.println("Input Account Number");
			walletAccount.setAccountNumber(Integer.parseInt(key.nextLine().trim()));
			if (customerDAO.walletAccount(walletAccount)) {
				System.out.println("Success");
			} else {
				System.out.println("Failed");
			}
//
		} catch (Exception e) {
//
			e.printStackTrace();
		}
	}

//	static void transactionType(TransactionTypeModel transactionType) {
//		TransactionTypeModel transactionTypeM = new TransactionTypeModel();
//		try {
//			System.out.println("Masukan Code");
//			transactionTypeM.setCode(Integer.parseInt(input.readLine()));
//			System.out.println("Masukan Description");
//			transactionTypeM.setDescription(input.readLine());
//			customerDAO.transactionType(transactionType);
//			if (customerDAO.transactionType(transactionTypeM)) {
//				System.out.println("berhasil");
//			} else {
//				System.out.println("gagal");
//			}
//
//		} catch (Exception e) {
//
//			e.printStackTrace();
//		}
//
//	}

	static void addTransaction() {
		TransactionModel transaction = new TransactionModel();
		try {
			System.out.println("============ Create Transaksi =============");

			System.out.println("Input Account Number Debit:");
			transaction.setAccountNumberDebit(Integer.parseInt(key.nextLine()));
			System.out.println("Input Account Number Credit:");
			transaction.setAccountNumberCredit(Integer.parseInt(key.nextLine()));
			System.out.println("Amount:");
			transaction.setAmount(Integer.parseInt(key.nextLine()));
			System.out.println(" Transaction Type:");
			transaction.setTransactionType(Integer.parseInt(key.nextLine()));
			if (customerDAO.transaction(transaction)) {
				System.out.println("Success");
			} else {
				System.out.println("Failed");
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public static void menu1() {

		boolean back = false;
		try {
			do {
				System.out.println("============ Menu Wallet ===========");
				System.out.println("1. Create Wallet Account");
				System.out.println("2. Create Wallet");
				System.out.println("3. Transaction");
				System.out.println("99. Backa To Main Menu");

				String pilihan = key.nextLine();
				if (pilihan.equals("99")) {
					menu();
				} else if (pilihan.equals("1")) {
					walletAccount();
				} else if (pilihan.equals("2")) {
					wallet();
				} else if (pilihan.equals("3")) {
					transaction();
				} else if (pilihan.equals("99")) {
					back = true;
				} else {
					System.out.println("Wrong Input !");
				}
			} while (!back);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public static void transaction() {
		System.out.println("================== Menu Transaction =================");
		System.out.println("1. Transfer:");
		System.out.println("2. Top-Up:");
		System.out.println("3. Cash Withdrawal :");
		System.out.println("89. Back Menu Wallet  :");

		String pilihan = key.nextLine();
		if (pilihan.equals("89")) {
			menu1();
		} else if (pilihan.equals("1")) {
			transfer();
			transaction();
		} else if (pilihan.equals("2")) {
			topUp();
			transaction();
		} else if (pilihan.equals("3")) {
			tarikTunai();
			transaction();
		} else {
			System.out.println("Wrong Input !");
		}
	}

	static void transfer() {
		try {
			TransactionModel tm = new TransactionModel();
			System.out.println("======== Transfer ==========");
			System.out.print("Account Number Debit: ");
			tm.setAccountNumberDebit(Integer.parseInt(key.nextLine()));
			System.out.print("Account Number Credit: ");
			tm.setAccountNumberCredit(Integer.parseInt(key.nextLine()));
			System.out.print("Amount: ");
			tm.setAmount(Integer.parseInt(key.nextLine()));
			System.out.print("Transaction Type: ");
			tm.setTransactionType(Integer.parseInt(key.nextLine()));
			customerDAO.transfer(tm);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void topUp() {
		try {
			TransactionModel transaction = new TransactionModel();
			System.out.println("======== Top Up ==========");
			System.out.print("Account Number Debit: ");
			transaction.setAccountNumberDebit(Integer.parseInt(key.nextLine()));
			System.out.print("Amount: ");
			transaction.setAmount(Integer.parseInt(key.nextLine()));
			System.out.print("Transaction Type: ");
			transaction.setTransactionType(Integer.parseInt(key.nextLine()));
			customerDAO.topUp(transaction);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void tarikTunai() {
		try {
			TransactionModel transaction = new TransactionModel();
			System.out.println("======== Cash Withdrawal ==========");
			System.out.print("Account Number Credit: ");
			transaction.setAccountNumberCredit(Integer.parseInt(key.nextLine()));
			System.out.print("Amount: ");
			transaction.setAmount(Integer.parseInt(key.nextLine()));
			System.out.print("Transaction Type: ");
			transaction.setTransactionType(Integer.parseInt(key.nextLine()));
			customerDAO.transfer(transaction);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
