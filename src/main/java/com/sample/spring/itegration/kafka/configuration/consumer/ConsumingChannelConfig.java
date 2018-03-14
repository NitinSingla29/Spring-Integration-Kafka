package com.sample.spring.itegration.kafka.configuration.consumer;


import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ConsumingChannelConfig {
  @Value("${kafka.bootstrap.servers}")
  private String bootstrapServers;

  @Value("${kafka.topic.test1}")
  private String springIntegrationKafkaTopic;

  @Value("${kafka.conumer.group.id}")
  private String consumerGroupId;

  @Value("${kafka.auto.offset.rest.conf}")
  private String autoOffsetResetConfiguration;

  @Bean
  ConcurrentKafkaListenerContainerFactory<Integer, String> kafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<Integer, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());
    return factory;
  }

  @Bean
  public ConsumerFactory<Integer, String> consumerFactory() {
    return new DefaultKafkaConsumerFactory<>(consumerConfigs());
  }

  @Bean
  public Map<String, Object> consumerConfigs() {
    Map<String, Object> properties = new HashMap<>();
    properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    properties.put(ConsumerConfig.GROUP_ID_CONFIG, consumerGroupId);
    properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
    properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "100");
    properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000");
    // automatically reset the offset to the earliest offset
    properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetResetConfiguration);

    return properties;
  }
}
