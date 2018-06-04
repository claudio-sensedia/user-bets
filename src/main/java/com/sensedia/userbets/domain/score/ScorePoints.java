package com.sensedia.userbets.domain.score;

import lombok.Getter;

@Getter
public enum ScorePoints {
  MATCH_SCORE(25),
  WINNER_SCORE(18),
  VARIATION_SCORE(15),
  LOSER_SCORE(12),
  WINNER(10);

  private Integer points;

  ScorePoints(int points) {
    this.points = points;
  }
}
