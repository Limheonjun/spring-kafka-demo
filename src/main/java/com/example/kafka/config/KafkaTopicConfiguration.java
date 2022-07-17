package com.example.kafka.config;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaTopicConfiguration {

	/**
	 * 단일 토픽 생성
	 */
	@Bean
	public NewTopic newTopic() {
		return TopicBuilder.name("coin").build();
	}

	/**
	 * 멀티 토픽 생성
	 */
	@Bean
	public KafkaAdmin.NewTopics newToPics() {
		return new KafkaAdmin.NewTopics(
			TopicBuilder.name("bitcoin").build(),
			TopicBuilder.name("ethereum-bytes").build(),
			TopicBuilder.name("ethereum-request").build(),
			TopicBuilder.name("ethereum-replies").build(),
			TopicBuilder.name("ethereum")
				.partitions(3) //파티션 개수 설정
				.replicas(1) //레플리카 개수 설정
				.config(TopicConfig.RETENTION_MS_CONFIG, String.valueOf(1000 * 60 * 60)) //1시간의 리텐션 시간 설정
				.build()
		);
	}

	@Bean
	public AdminClient adminClient(KafkaAdmin kafkaAdmin) {
		return AdminClient.create(kafkaAdmin.getConfigurationProperties());
	}

}
