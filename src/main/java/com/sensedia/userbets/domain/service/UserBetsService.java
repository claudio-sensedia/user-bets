package com.sensedia.userbets.domain.service;

import com.sensedia.userbets.domain.MatchResult;
import com.sensedia.userbets.domain.UserBet;
import com.sensedia.userbets.domain.UserPointsResult;
import com.sensedia.userbets.domain.repository.UserBetsRepository;
import com.sensedia.userbets.domain.score.ScorePoints;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class UserBetsService {

  private final UserBetsRepository userBetsRepository;
  private final RankingService rankingService;

  public UserBetsService(
      UserBetsRepository userBetsRepository,
      RankingService rankingService) {
    this.userBetsRepository = userBetsRepository;
    this.rankingService = rankingService;
  }

  public UserBet makeBet(UserBet userBet) {
    userBet.setBetId(UUID.randomUUID().toString());
    userBet.setRegisteredAt(LocalDateTime.now());
    return this.userBetsRepository.save(userBet);
  }

  public void sendScore(MatchResult matchResult) {
    List<UserBet> lsBets = this.userBetsRepository.findByMatchId(matchResult.getMatchId());

    lsBets.forEach(userBet -> {
      this.rankingService.send(UserPointsResult.builder().userId(userBet.getUserId())
          .betId(userBet.getBetId()).points(this.getScorePoints(userBet, matchResult)).build());
    });
  }

  private Long getScorePoints(UserBet userBet, MatchResult matchResult) {
    Long sumPoints = 0L;

    if (userBet.getHomeResult().equals(matchResult.getHomeResult()) && userBet.getAwayResult()
        .equals(matchResult.getAwayResult())) {
      sumPoints += ScorePoints.MATCH_SCORE.getPoints();
    }

    if ((userBet.getHomeResult() > userBet.getAwayResult()
        && matchResult.getHomeResult() > matchResult.getAwayResult()) || (
        userBet.getHomeResult() < userBet.getAwayResult()
            && matchResult.getHomeResult() < matchResult.getAwayResult())) {
      sumPoints += ScorePoints.WINNER.getPoints();
    }

    if (userBet.getHomeResult() - userBet.getAwayResult()
        == matchResult.getHomeResult() - matchResult.getAwayResult()) {
      sumPoints += ScorePoints.VARIATION_SCORE.getPoints();
    }

    if ((matchResult.getHomeResult() > matchResult.getAwayResult() && userBet.getHomeResult()
        .equals(matchResult.getHomeResult())) || (
        matchResult.getHomeResult() < matchResult.getAwayResult() && userBet.getAwayResult()
            .equals(matchResult.getAwayResult()))) {
      sumPoints += ScorePoints.WINNER_SCORE.getPoints();
    }

    if ((matchResult.getHomeResult() > matchResult.getAwayResult() && userBet.getAwayResult()
        .equals(matchResult.getAwayResult())) || (
        matchResult.getHomeResult() < matchResult.getAwayResult() && userBet.getHomeResult()
            .equals(matchResult.getHomeResult()))) {
      sumPoints += ScorePoints.LOSER_SCORE.getPoints();
    }

    return sumPoints;
  }

}
