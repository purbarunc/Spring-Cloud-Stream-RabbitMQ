package org.purbarun.cloudstream.config;

import java.util.function.Consumer;

import org.purbarun.cloudstream.model.OrderMessage;
import org.purbarun.cloudstream.service.OrderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumerConfig {
	private OrderService orderService;

	public ConsumerConfig(OrderService orderService) {
		this.orderService = orderService;
	}

	@Bean
	Consumer<OrderMessage> onReceive() {
		return orderMessage -> orderService.process(orderMessage);
	}
}
