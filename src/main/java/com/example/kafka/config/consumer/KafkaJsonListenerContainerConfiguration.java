package com.example.kafka.config.consumer;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListenerConfigurer;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerEndpointRegistrar;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.example.kafka.model.Animal;

@Configuration
public class KafkaJsonListenerContainerConfiguration implements KafkaListenerConfigurer {

	@Autowired
	private LocalValidatorFactoryBean validator;

	@Bean
	public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Animal>> kafkaJsonContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, Animal> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(animalConsumerFactory());
		return factory;
	}

	private ConsumerFactory<String, Animal> animalConsumerFactory() {
		return new DefaultKafkaConsumerFactory<>(
			props(),
			new StringDeserializer(),
			new JsonDeserializer<>(Animal.class)
		);
	}

	private Map<String, Object> props() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		return props;
	}

	@Override
	public void configureKafkaListeners(KafkaListenerEndpointRegistrar registrar) {
		registrar.setValidator(validator); //벨리데이터 지정
	}
}
