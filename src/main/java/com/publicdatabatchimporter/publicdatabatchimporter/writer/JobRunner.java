package com.publicdatabatchimporter.publicdatabatchimporter.writer;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class JobRunner implements CommandLineRunner {

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job importJob;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Starting the job...");
		jobLauncher.run(importJob, new JobParametersBuilder()
			.addLong("startAt", System.currentTimeMillis())
			.toJobParameters());
		System.out.println("Job execution completed.");
	}
}
