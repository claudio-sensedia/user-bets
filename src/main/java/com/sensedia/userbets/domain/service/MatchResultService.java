package com.sensedia.userbets.domain.service;

import com.sensedia.userbets.domain.MatchResult;
import com.sensedia.userbets.domain.UserBet;
import com.sensedia.userbets.domain.repository.UserBetsRepository;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
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
