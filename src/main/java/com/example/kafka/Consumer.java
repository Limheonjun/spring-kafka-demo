package com.example.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

	@KafkaListener(id = "fast-campus-id2", topics = "quickstart-events")
	public void listen(String message) {
		System.out.println("=======");
		System.out.println(message);
		System.out.println("=======");
	}
}
