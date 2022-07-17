package com.example.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {

	public void produce(KafkaTemplate<String, String> kafkaTemplate) {
		kafkaTemplate.send("quickstart-events", "blockchain");
	}

}
