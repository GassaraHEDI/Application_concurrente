package com.onepoint.enseirb.firstProject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;

@Controller
public class CityLogger {
	private final Logger log = LoggerFactory.getLogger(CityLogger.class);

	@KafkaListener(id = "hgog-city", topics = "cities")
	public void received(String city) {
		//log.info("city received {}", city);
	}

}
