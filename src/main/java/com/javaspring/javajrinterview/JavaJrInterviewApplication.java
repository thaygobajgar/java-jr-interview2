package com.javaspring.javajrinterview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class JavaJrInterviewApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();

		System.setProperty("spring.datasource.url", dotenv.get("DB_URL"));
        System.setProperty("spring.datasource.username", dotenv.get("DB_USERNAME"));
        System.setProperty("spring.datasource.password", dotenv.get("DB_PASSWORD"));
        System.setProperty("spring.mail.host", dotenv.get("EMAIL_HOST"));
        System.setProperty("spring.mail.port", dotenv.get("EMAIL_PORT"));
        System.setProperty("spring.mail.username", dotenv.get("EMAIL_USERNAME"));
        System.setProperty("spring.mail.password", dotenv.get("EMAIL_PASSWORD"));
		
		SpringApplication.run(JavaJrInterviewApplication.class, args);
	}

}
