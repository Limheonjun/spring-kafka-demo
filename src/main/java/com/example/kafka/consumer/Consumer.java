package com.example.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

	@KafkaListener(id = "consumer1", topics = "ethereum")
	public void listen(String message) {
		System.out.println("=======");
		System.out.println(message);
		System.out.println("=======");
	}

	@KafkaListener(id = "ethereum-bytes-id", topics = "ethereum-bytes")
	public void listenBytes(String message) {
		System.out.println(message);
	}

	@KafkaListener(id = "ethereum-request-id", topics = "ethereum-request")
	@SendTo
	public String listenRequest(String message) {
		System.out.println(message);
		return "Reply Ethereum";
	}
}
