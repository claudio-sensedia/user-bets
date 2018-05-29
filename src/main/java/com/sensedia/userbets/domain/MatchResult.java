package com.sensedia.userbets.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MatchResult {

  private String matchId;
  private Long homeResult;
  private Long awayResult;
}
