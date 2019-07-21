package com.example.broker;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BrokerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BrokerApplication.class, args);
	}

	@Bean
	public CommandLineRunner listen(PubSubTemplate pubSubTemplate){
		return args -> {
			pubSubTemplate.subscribe("newHouses-broker1",
					(message, consumer)->{
						System.out.println("New house for salve" + message.getData().toStringUtf8());
					}
			);
		};
	}
}
