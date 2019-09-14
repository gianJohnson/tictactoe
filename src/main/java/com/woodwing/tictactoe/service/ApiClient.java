package com.woodwing.tictactoe.service;


import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

@Service
public class ApiClient {


    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    private final RestTemplate restTemplate;

    public ApiClient(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    public ResponseEntity<String>  callUrl(String baseUrl) throws URISyntaxException {
        return restTemplate.getForEntity(new URI(baseUrl) , String.class);
    }



}

