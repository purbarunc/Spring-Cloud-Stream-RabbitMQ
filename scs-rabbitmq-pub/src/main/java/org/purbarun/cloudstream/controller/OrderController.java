package org.purbarun.cloudstream.controller;

import java.time.OffsetDateTime;
import java.util.UUID;

import org.purbarun.cloudstream.model.OrderMessage;
import org.purbarun.cloudstream.model.OrderRequest;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class OrderController {
	private StreamBridge streamBridge;

	public OrderController(StreamBridge streamBridge) {
		this.streamBridge = streamBridge;
	}

	@PostMapping("/order")
	public ResponseEntity<String> placeOrder(@RequestBody OrderRequest orderRequest) {
		String msgId = UUID.randomUUID().toString();
		OrderMessage orderMessage = new OrderMessage(orderRequest, msgId);
		Message<OrderMessage> message = MessageBuilder.withPayload(orderMessage)
				.setHeader("dateTime", OffsetDateTime.now()).build();
		streamBridge.send("Order_Exchange", message);
		log.info("Order Published to RabbitMQ => {}", orderMessage);
		String responseMeassge = "Order Received with Message Id:" + msgId;
		return new ResponseEntity<>(responseMeassge, HttpStatus.OK);
	}
}
