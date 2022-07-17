package com.example.kafka.consumer;

import javax.validation.Valid;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.adapter.ConsumerRecordMetadata;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import com.example.kafka.model.Animal;

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

	@KafkaListener(
		id = "ethereum-listener-id",
		topics = "ethereum",
		concurrency = "2", //스레드의 개수 지정
		clientIdPrefix = "listener-id"
	)
	public void listen2(
		String message,
		ConsumerRecordMetadata metadata, //ConsumerRecordMetadata를 파라미터로 받아 헤더정보 가져오기
		//아래와 같이 애노테이션을 사용해서 헤더 정보를 가져올 수도 있음
		@Header(KafkaHeaders.RECEIVED_TIMESTAMP) long timestamp,
		@Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
		@Header(KafkaHeaders.RECEIVED_TIMESTAMP) long offset
	) {
		System.out.println("Listener offset=" + metadata.offset() +
			", partition=" + partition +
			", timestamp=" + timestamp +
			" / message=" + message);
	}

	@KafkaListener(id = "ethereum-animal-listener", topics = "ethereum-animal", containerFactory = "kafkaJsonContainerFactory")
	public void listenAnimal(@Valid Animal animal) {
		System.out.println("Animal. animal=" + animal);
	}
}
