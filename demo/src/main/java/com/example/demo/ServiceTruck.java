package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class ServiceTruck {
    public static final String TOPIC = "trucks.alert";
    private final Logger log = LoggerFactory.getLogger(ServiceTruck.class);

    @Autowired
    WebClient truckClient;
    @Autowired
    KafkaTemplate<String , TruckPosition > kafkaTemplate;


    public String post(Position position) {
        return truckClient.post().uri("/map").body(Mono.just(position), Position.class).retrieve().bodyToMono(String.class).block();
    }

    @Async
    public void positionChecker(TruckPosition truckPosition){
        int alert =  truckClient.post().uri("/position/check").body(Mono.just(truckPosition), TruckPosition.class).retrieve().bodyToMono(Integer.class).block();
        if (alert > 0){
            kafkaTemplate.send(TOPIC, String.valueOf(truckPosition.getTruckId()), truckPosition);
            log.info("Alert =  "+String.valueOf(alert));
        }
    }
}
