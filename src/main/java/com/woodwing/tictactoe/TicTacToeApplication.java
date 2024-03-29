package com.woodwing.tictactoe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class TicTacToeApplication {
	private static final Logger log = LoggerFactory.getLogger(TicTacToeApplication.class);
    
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
    	RestTemplate restTemplate = builder.build();
    	return restTemplate;
    }
    
	public static void main(String[] args) {
    	SpringApplication.run(TicTacToeApplication.class);
    }    
}
