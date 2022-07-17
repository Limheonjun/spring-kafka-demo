package com.example.kafka.config;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.core.RoutingKafkaTemplate;

@Configuration
public class RoutingKafkaTemplateConfiguration {

	@Bean
	public RoutingKafkaTemplate routingKafkaTemplate() {
		return new RoutingKafkaTemplate(factories());
	}

	//라우팅카프카템플릿의 벨류가 <Object,Object>인 이유는
	//어떤 형식으로 직렬화가 이루어질지 모르기 때문
	private Map<Pattern, ProducerFactory<Object, Object>> factories() {
		Map<Pattern, ProducerFactory<Object, Object>> factories = new LinkedHashMap<>();
		factories.put(Pattern.compile("ethereum-bytes"), byteProducerFactory()); //이더리움 바이트 토픽은 byteProducer의 설정을 이용
		factories.put(Pattern.compile(".*"), defaultProducerFatory());
		return factories;
	}

	private ProducerFactory<Object, Object> byteProducerFactory() {
		Map<String, Object> props = producerProps();
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class);//벨류 시리얼라이저
		return new DefaultKafkaProducerFactory<>(props);
	}

	private ProducerFactory<Object, Object> defaultProducerFatory() {
		return new DefaultKafkaProducerFactory<>(producerProps());
	}

	private Map<String, Object> producerProps() {
		Map<String, Object> props = new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092"); //클러스터
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);//키 시리얼라이저
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);//벨류 시리얼라이저
		return props;
	}

}
