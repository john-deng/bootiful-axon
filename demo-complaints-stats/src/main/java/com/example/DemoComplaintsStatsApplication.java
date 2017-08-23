package com.example;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.amqp.eventhandling.DefaultAMQPMessageConverter;
import org.axonframework.amqp.eventhandling.spring.SpringAMQPMessageSource;
import org.axonframework.serialization.Serializer;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class DemoComplaintsStatsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoComplaintsStatsApplication.class, args);
	}

	@Bean
	public SpringAMQPMessageSource statisticsQueue(Serializer serializer) {
		return new SpringAMQPMessageSource(new DefaultAMQPMessageConverter(serializer)) {

			@RabbitListener(queues = "Complaints")
			@Override
			public void onMessage(Message message, Channel channel) throws Exception {
				log.info("onMessage()");
				super.onMessage(message, channel);
			}
		};
	}
}
