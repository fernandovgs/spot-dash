package com.spot.dash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpotDashProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpotDashProducerApplication.class, args);
	}

}
