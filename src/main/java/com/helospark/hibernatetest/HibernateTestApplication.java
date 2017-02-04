package com.helospark.hibernatetest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Entry point for the test application.
 * Run it with {@link HibernateTestApplicationTests}
 * @author helospark
 */
@SpringBootApplication
@ComponentScan
public class HibernateTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(HibernateTestApplication.class, args);
	}
}
