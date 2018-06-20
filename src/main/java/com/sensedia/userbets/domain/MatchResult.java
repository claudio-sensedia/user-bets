package com.sensedia.userbets.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MatchResult {

  @JsonProperty("name")
  private String matchId;
  private Long homeResult;
  private Long awayResult;
}
