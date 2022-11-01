package com.onepoint.enseirb.firstProject;

import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component 
public class CityProd {
	public static final String TOPIC = "log";
	private final Logger log = LoggerFactory.getLogger(CityProd.class);
	
	@Autowired 
	KafkaTemplate<String , String > kafkaTemplate;
	
	@EventListener(ContextRefreshedEvent.class)
	public void onApplicationStarted() {
		log.info("Application starting");
		kafkaTemplate.send(TOPIC,"hgog app starting at "+Instant.now());
	}
	
}
