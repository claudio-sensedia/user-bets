package com.sensedia.userbets.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserPointsResult implements Serializable {

  private static final long serialVersionUID = 8562013900737282786L;
  @JsonProperty("user_id")
  private String userId;
  @JsonProperty("bet_id")
  private String betId;
  private Long points;
  @JsonProperty("registered_at")
  private LocalDateTime registeredAt;
  @JsonProperty("match_id")
  private String matchId;
}
