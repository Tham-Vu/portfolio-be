package com.example.portfoliobe;

import com.example.portfoliobe.configs.PropertiesConfiguration;
import com.example.portfoliobe.jwt.JwtUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PortfolioBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortfolioBeApplication.class, args);
	}


}
