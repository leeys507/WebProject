package com.wp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

@EnableJpaAuditing
@SpringBootApplication
public class WebProjectApplication {
	
	@PostConstruct
	public void started() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC+9"));
	}
	
	public static void main(String[] args) {
		SpringApplication.run(WebProjectApplication.class, args);
	}
}
