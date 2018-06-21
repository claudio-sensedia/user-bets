package com.sensedia.userbets.domain.service;

import com.sensedia.userbets.domain.MatchResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MatchResultService {

  private UserBetsService userBetsService;

  public MatchResultService(UserBetsService userBetsService) {
    this.userBetsService = userBetsService;
  }

  @RabbitListener(queuesToDeclare = {@Queue("${match.result.queue}")})
  public void process(MatchResult matchResult){
    log.info("Recived message from rabbitmq");
    this.userBetsService.sendScore(matchResult);
  }
}
