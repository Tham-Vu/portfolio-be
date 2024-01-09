package com.example.portfoliobe;

import com.example.portfoliobe.configs.PropertiesConfiguration;
import com.example.portfoliobe.jwt.JwtUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PortfolioBeApplication implements CommandLineRunner {
	private final JwtUtils jwtUtils;
	private static final PropertiesConfiguration properties = new PropertiesConfiguration();

	public PortfolioBeApplication(JwtUtils jwtUtils) {
		this.jwtUtils = jwtUtils;
	}


	public static void main(String[] args) {
		SpringApplication.run(PortfolioBeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		jwtUtils.display();
	}


}
