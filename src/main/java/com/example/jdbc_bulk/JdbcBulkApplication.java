package com.example.jdbc_bulk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SpringBootApplication
public class JdbcBulkApplication {

	private static final Logger log = LoggerFactory.getLogger(JdbcBulkApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(JdbcBulkApplication.class, args);
	}

}