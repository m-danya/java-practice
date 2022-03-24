package com.webapp.bankapp;

import com.webapp.bankapp.models.Account;
import com.webapp.bankapp.models.Branch;
import com.webapp.bankapp.utils.DAOFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigInteger;
import java.util.List;

@SpringBootApplication
public class BankappApplication {

	public static void main(String[] args) {
		System.out.println("hello");
		SpringApplication.run(BankappApplication.class, args);

		Branch b = new Branch("Отделение на Краснопресненской", "г. Москва, ул. Заморёнова, 5 стр 1");
		DAOFactory.getInstance().getBranchDAO().add(b);

		BigInteger from = BigInteger.valueOf(0);
		BigInteger to = BigInteger.valueOf(15000);
		List<Account> lowBalanceAccounts = DAOFactory.getInstance().
				getAccountDAO().find_by_balance_range(from, to);
		for (var account : lowBalanceAccounts) {
			System.out.println(account.toString());
		}
	}

}
