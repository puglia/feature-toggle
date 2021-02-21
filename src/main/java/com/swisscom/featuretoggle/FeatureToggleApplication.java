package com.swisscom.featuretoggle;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.swisscom.featuretoggle.model.Feature;
import com.swisscom.featuretoggle.repository.FeatureRepository;

@SpringBootApplication
public class FeatureToggleApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeatureToggleApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(FeatureRepository repository) {
		return (args) ->{
			Feature feature = repository.findById(1L).get();
			System.out.println(feature.getCustomers().get(0)); 
		} ;
		
	}

}
