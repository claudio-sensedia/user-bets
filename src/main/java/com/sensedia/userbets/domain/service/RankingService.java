package com.sensedia.userbets.domain.service;

import com.sensedia.userbets.domain.UserPointsResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RankingService {

  private final RabbitTemplate rabbitTemplate;

  private final String destination;

  public RankingService(RabbitTemplate rabbitTemplate,
      @Value("${ranking.user.queue}") String destination) {
    this.rabbitTemplate = rabbitTemplate;
    this.destination = destination;
  }

  @Async
  public void send(UserPointsResult userPointsResult) {
    log.info("Send message -> betId={}, points={}, userId={}", userPointsResult.getBetId(),
        userPointsResult.getPoints(), userPointsResult.getUserId());
    this.rabbitTemplate.convertAndSend(this.destination, userPointsResult);
  }
}
