package com.webapp.bankapp;

import com.webapp.bankapp.models.Branch;
import com.webapp.bankapp.utils.DAOFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankappApplication {

	public static void main(String[] args) {
		System.out.println("hello");
		SpringApplication.run(BankappApplication.class, args);

		Branch b = new Branch("Отделение на Краснопресненской", "г. Москва, ул. Заморёнова, 5 стр 1");
		DAOFactory.getInstance().getBranchDAO().add(b);
	}

}
