package com.sensedia.userbets.domain.repository;

import com.sensedia.userbets.domain.UserBet;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface UserBetsRepository extends CrudRepository<UserBet, String> {

  List<UserBet> findByMatchId(String matchId);

  Iterable<UserBet> findByUserId(String userId);
}
