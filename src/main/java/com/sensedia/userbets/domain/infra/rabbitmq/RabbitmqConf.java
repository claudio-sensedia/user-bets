package com.sensedia.userbets.domain.infra.rabbitmq;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RabbitmqConf {

  private final String queue;
  private final String exchange;
  private final String key;

  public RabbitmqConf(@Value("${ranking.user.queue}") String queue,
      @Value("${ranking.exchange}") String exchange,
      @Value("${ranking.user.key}") String key) {
    this.queue = queue;
    this.exchange = exchange;
    this.key = key;
  }

  @Bean("rankingExchange")
  @Primary
  public DirectExchange notificationExchange() {
    return new DirectExchange(this.exchange);
  }

  @Bean("rankingUserQueue")
  public Queue notificationQueue() {
    return new Queue(this.queue);
  }

  @Bean
  public MessageConverter jsonMessageConverter() {
    return new Jackson2JsonMessageConverter();
  }

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
