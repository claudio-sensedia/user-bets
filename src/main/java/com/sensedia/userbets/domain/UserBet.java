package com.sensedia.userbets.domain;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "user_bets")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserBet {

  @Id
  @Field("bet_id")
  private String betId;

  @Field("registered_at")
  private LocalDateTime registeredAt;

  @Field("user_id")
  private String userId;

  @Field("match_id")
  private String matchId;

  @Field("home_result")
  private Long homeResult;

  @Field("away_result")
  private Long awayResult;

}
