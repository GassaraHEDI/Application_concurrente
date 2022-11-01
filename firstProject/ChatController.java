package com.onepoint.enseirb.firstProject;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class ChatController {
	public static final String TOPIC = "msg.txt";
	private final Logger log = LoggerFactory.getLogger(ChatController.class);
	private List<String> messages = new ArrayList<String>();
	
	@Autowired 
	KafkaTemplate<String , String > kafkaTemplate;
	
	@KafkaListener(id = "hgog-chat", topics = TOPIC)
	public void received(String message) {
		log.info("message received {}", message);
		messages.add(message);
	}
	
	@PostMapping
	public void newMessage( @RequestBody String message) {
		log.info(message);
		kafkaTemplate.send(TOPIC,message);
	}
	
	@GetMapping
	public List<String> getMessages() {
		if(messages.size()<10) {
			return messages;
		}
		List<String> myLastMessages = messages.subList(messages.size()-10, messages.size());
		return myLastMessages;
	}
}
