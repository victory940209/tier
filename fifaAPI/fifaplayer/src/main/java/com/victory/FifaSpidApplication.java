package com.victory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = {"classpath:/properties/server-${spring.profiles.active}.properties"})
public class FifaSpidApplication {

	public static void main(String[] args) {

		SpringApplication api = new SpringApplication(FifaSpidApplication.class);
		api.addListeners(new ApplicationPidFileWriter());
		api.run(args);

	}

}
