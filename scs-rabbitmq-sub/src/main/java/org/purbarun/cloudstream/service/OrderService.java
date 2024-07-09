package org.purbarun.cloudstream.service;

import org.purbarun.cloudstream.model.OrderMessage;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderService {
	public void process(OrderMessage orderMessage) {
		log.info("Order Received from RabbitMQ => {}", orderMessage);
	}
}
