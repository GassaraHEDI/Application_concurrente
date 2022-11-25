package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class ServiceTruck {
    @Autowired
    WebClient truckClient;



    public String post(Position position) {
        return truckClient.post().uri("/map").body(Mono.just(position), Position.class).retrieve().bodyToMono(String.class).block();
    }
}
