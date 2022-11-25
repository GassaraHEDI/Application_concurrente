package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class TruckClient {

    public static final String TRUCK_URL =
            "http://breisen.datamix.ovh:8080";

    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder.baseUrl(TRUCK_URL).build();
    }
}
