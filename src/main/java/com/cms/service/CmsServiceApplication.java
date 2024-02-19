package com.cms.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * The main class for the CMS (Content Management System) Service Application.
 * This class initializes and starts the Spring Boot application.
 */
@SpringBootApplication
public class CmsServiceApplication {

	
	/**
     * The main method of the CMS Service Application.
     * It starts the Spring Boot application by running the SpringApplication.run() method.
     *
     * @param args The command-line arguments passed to the application.
     */
	public static void main(String[] args) {
		SpringApplication.run(CmsServiceApplication.class, args);
	}

}
