package com.golo.golomonitorapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GoloMonitorApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoloMonitorApiApplication.class, args);
	}

}
