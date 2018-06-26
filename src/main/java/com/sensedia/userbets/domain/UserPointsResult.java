package com.sensedia.userbets.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
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
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonProperty("registered_at")
  private LocalDateTime registeredAt;
  @JsonProperty("match_id")
  private String matchId;
}
