package com.example.tomokiiwai;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application
 *
 * @author tomoki.iwai
 */
@SpringBootApplication
public class D3SpringMailApplication implements CommandLineRunner {
	/**
	 * Constructor
	 */
	public D3SpringMailApplication() {
	}

	/**
	 * entry point
	 */
	public static void main(String[] args) {
		SpringApplication.run(D3SpringMailApplication.class, args);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void run(String... args) throws Exception {
	}

}
