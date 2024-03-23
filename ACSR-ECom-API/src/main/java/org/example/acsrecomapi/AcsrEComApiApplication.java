package org.example.acsrecomapi;

import org.example.acsrecomapi.Config.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AcsrEComApiApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(AcsrEComApiApplication.class);
		application.addListeners(new SwaggerConfig());
		application.run(args);
	}

}
