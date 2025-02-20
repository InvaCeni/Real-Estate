package com.example.Real.Estate.Management.System;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@EnableMethodSecurity
@SpringBootApplication
public class RealEstateManagementSystemApplication {

	public static void main(String[] args) {

		SpringApplication.run(RealEstateManagementSystemApplication.class, args);
	}

}



