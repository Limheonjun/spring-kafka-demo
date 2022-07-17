package com.example.kafka.config.consumer;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;

@Configuration
public class MessageListenerContainerConfiguration {

	@Bean
	public KafkaMessageListenerContainer<String, String> kafkaMessageListenerContainer() {
		ContainerProperties containerProps = new ContainerProperties("ethereum");
		containerProps.setGroupId("ethereum-container"); //group id는 필수, 없으면 에러 발생
		containerProps.setAckMode(ContainerProperties.AckMode.BATCH);
		containerProps.setMessageListener(new DefaultMessageListener());
		KafkaMessageListenerContainer<String, String> container = new KafkaMessageListenerContainer<>(
			containerFactory(), containerProps);
		container.setAutoStartup(false); //false인 경우 스프링 컨텍스트가 시작이 되더라도 자동으로 올라가지 않음
		return container;
	}

	private ConsumerFactory<String, String> containerFactory() {
		return new DefaultKafkaConsumerFactory<>(props());
	}

	private Map<String, Object> props() {
		HashMap<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		return props;
	}

}
