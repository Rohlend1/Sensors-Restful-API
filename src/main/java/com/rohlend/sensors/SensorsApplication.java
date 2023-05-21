package com.rohlend.sensors;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SensorsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SensorsApplication.class, args);
	}

	@Bean
	ModelMapper getModelMapper(){
		return new ModelMapper();
	}

}
