package com.fada.project3t5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@SpringBootApplication
public class Project3t5Application {

	public static void main(String[] args) {
		SpringApplication.run(Project3t5Application.class, args);
	}

}
