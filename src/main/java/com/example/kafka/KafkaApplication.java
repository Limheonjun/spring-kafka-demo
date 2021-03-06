package com.example.kafka;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.TopicDescription;
import org.apache.kafka.clients.admin.TopicListing;
import org.apache.kafka.common.KafkaFuture;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;

import com.example.kafka.model.Animal;
import com.example.kafka.producer.Producer;

@SpringBootApplication
public class KafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaApplication.class, args);

	}

	@Bean
	public ApplicationRunner runner(AdminClient adminClient) {
		return args -> {
			/**
			 * 토픽 리스트 출력
			 */
			// Map<String, TopicListing> topics = adminClient.listTopics().namesToListings().get();
			// for(String topicName: topics.keySet()) {
			// 	TopicListing topicListing = topics.get(topicName);
			// 	System.out.println(topicListing);
			//
			// 	//토픽의 세부정보
			// 	KafkaFuture<Map<String, TopicDescription>> description = adminClient.describeTopics(
			// 		Collections.singleton(topicName)).allTopicNames();
			// 	System.out.println("[Detail] : " + description);
			//
			// 	//토픽 삭제
			// 	adminClient.deleteTopics(Collections.singleton(topicName));
			// }


		};
	}

	@Bean
	public ApplicationRunner runner2(Producer producer) {
		return args -> {
			// producer.async("ethereum", "Hello async");
			// producer.sync("ethereum", "Hello async");
			// producer.routingSend("ethereum", "Hello routingSend");
			// producer.routingSendBytes("ethereum-bytes", "Hello bytes".getBytes(StandardCharsets.UTF_8));
			// producer.replyingSend("ethereum-request", "Response Ethereum");
		};
	}

	/**
	 * 리스너 컨테이너의 수동 조작
	 */
	@Bean
	public ApplicationRunner runner3(
		Producer producer,
		KafkaMessageListenerContainer<String, String> container
	) {
		return args -> {
			// producer.async("ethereum", "Hello ETH Container");
			// container.start();
			// Thread.sleep(1000);
			//
			// System.out.println("-- pause --");
			// container.pause();
			// Thread.sleep(5000);
			//
			// producer.async("ethereum", "Hello ETH Container Second");
			//
			//
			// System.out.println("-- resume --");
			// container.resume();
			// Thread.sleep(1000);
			//
			// System.out.println("-- stop --");
			// container.stop();
		};
	}

	@Bean
	public ApplicationRunner runner4(Producer producer) {
		return args -> {
			// producer.async("ethereum", "Hello ETH 2.0 2");
		};
	}

	@Bean
	public ApplicationRunner kafkaJsonTemplateRunner(Producer producer) {
		return args -> {
			producer.async("ethereum-animal", new Animal("puppy", 15));
		};
	}

}
