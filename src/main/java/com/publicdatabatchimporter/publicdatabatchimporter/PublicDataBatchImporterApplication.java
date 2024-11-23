package com.publicdatabatchimporter.publicdatabatchimporter;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class PublicDataBatchImporterApplication {

	public static void main(String[] args) {
		SpringApplication.run(PublicDataBatchImporterApplication.class, args);
	}

}
