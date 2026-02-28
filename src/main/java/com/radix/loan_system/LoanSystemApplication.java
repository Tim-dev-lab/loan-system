package com.radix.loan_system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.*;

@SpringBootApplication
public class LoanSystemApplication {


	private static final Logger LOG = LoggerFactory.getLogger(LoanSystemApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(LoanSystemApplication.class, args);

		LOG.info("""
                ---------------------------------------------------------- \
                \t Loan management Application is running! \
                ----------------------------------------------------------\s"""
		);
	}

}
