package com.example.democomplaints;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoComplaintsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoComplaintsApplication.class, args);
	}

	@Bean
	public Exchange exchange() {
		return ExchangeBuilder.fanoutExchange("Complaints").build();
	}

	@Bean
	public Queue queue() {
		return QueueBuilder.durable("Complaints").build();
	}

	@Bean
	public Binding binding() {
		return BindingBuilder.bind(queue()).to(exchange()).with("*").noargs();
	}

	@Autowired
	public void configure(AmqpAdmin admin) {
		admin.declareExchange(exchange());
		admin.declareQueue(queue());
		admin.declareBinding(binding());
	}

}
