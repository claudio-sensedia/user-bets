package com.sensedia.userbets.domain.service;

import com.sensedia.userbets.domain.MatchResult;
import com.sensedia.userbets.domain.UserBet;
import com.sensedia.userbets.domain.repository.UserBetsRepository;
import org.springframework.stereotype.Service;

@Service
public class UserBetsService {

  private UserBetsRepository userBetsRepository;

  public UserBetsService(
      UserBetsRepository userBetsRepository) {
    this.userBetsRepository = userBetsRepository;
  }

  public UserBet makeBet(UserBet userBet) {
    return this.userBetsRepository.save(userBet);
  }

  public void sendScore(MatchResult matchResult) {
    //Calcular e enviar pontuação
  }
}
