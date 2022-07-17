package com.example.kafka.config.producer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;

@Configuration
public class ReplyingKafkaTemplateConfiguration {

	// @Bean
	// public ReplyingKafkaTemplate<String, String, String> replyingKafkaTemplate(
	// 	ProducerFactory<String, String> producerFactory,
	// 	ConcurrentMessageListenerContainer<String, String> repliesContainer
	// ) {
	// 	return new ReplyingKafkaTemplate<>(producerFactory, repliesContainer);
	// }
	//
	// @Bean
	// public ConcurrentMessageListenerContainer repliesContainer(ConcurrentKafkaListenerContainerFactory<String, String> containerFactory) {
	// 	ConcurrentMessageListenerContainer<String, String> container = containerFactory.createContainer("ethereum");
	// 	container.getContainerProperties().setGroupId("ethereum-replies-container-id");
	// 	return container;
	// }

}
