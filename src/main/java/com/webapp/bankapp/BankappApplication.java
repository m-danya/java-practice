package com.webapp.bankapp;

import com.webapp.bankapp.models.Account;
import com.webapp.bankapp.models.Branch;
import com.webapp.bankapp.models.Client;
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


	}

}
