package com.sensedia.userbets.domain.resource;

import com.sensedia.userbets.domain.UserBet;
import com.sensedia.userbets.domain.service.UserBetsService;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bet")
public class UserBetsResource {

  private UserBetsService userBetsService;

  public UserBetsResource(UserBetsService userBetsService) {
    this.userBetsService = userBetsService;
  }

  @PostMapping
  public ResponseEntity<?> makeBet(@Valid @RequestBody UserBet userBet) {
    return ResponseEntity.ok(this.userBetsService.makeBet(userBet));
  }

}
