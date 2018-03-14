package com.sample.spring.itegration.kafka.configuration.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class Listener {
  public static Logger logger = LoggerFactory.getLogger(Listener.class);

  private final CountDownLatch latch1 = new CountDownLatch(1);

  @KafkaListener(id = "foo", topics = "test-topic1")
  public void listen1(String message) {
    logger.debug("Received message {}", message);
    System.out.println("Received message : " + message);
    this.latch1.countDown();
  }
}
