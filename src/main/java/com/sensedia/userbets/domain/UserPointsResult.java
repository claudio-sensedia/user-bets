package com.sensedia.userbets.domain;

import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserPointsResult implements Serializable {

  private static final long serialVersionUID = 8562013900737282786L;
  private String userId;
  private String betId;
  private Long points;
}
