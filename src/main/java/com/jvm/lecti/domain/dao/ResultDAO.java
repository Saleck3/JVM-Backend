package com.jvm.lecti.domain.dao;

import com.jvm.lecti.domain.entity.Result;

import java.util.List;
import java.util.Optional;

public interface ResultDAO {

   Result save(Result result);

   Optional<Result> findByAppleIdAndPlayerId(Integer appleId, Integer playerId);

   Integer findTotalScoreByAppleIdAndPlayerId(List<Integer> appleIds, Integer playerId);

   List<Result> findAllByPlayerId(Integer playerId);

}
