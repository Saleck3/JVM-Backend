package com.jvm.lecti.infraestructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jvm.lecti.domain.entity.Result;

@Repository
public interface ResultRepository extends JpaRepository<Result, Integer> {

   @Query("SELECT r FROM Result r JOIN r.apple a JOIN r.player p WHERE a.id = :appleId AND p.id = :playerId")
   Optional<Result> findByAppleIdAndPlayerId(Integer appleId, Integer playerId);

   @Query("SELECT SUM(r.score) FROM Result r WHERE r.player.id = :playerId AND r.apple.id IN (:appleIds)")
   Integer findTotalScoreByAppleIdAndPlayerId(List<Integer> appleIds, Integer playerId);

   @Query("SELECT r FROM Result r JOIN r.player p WHERE p.id = :playerId")
   List<Result>findAllByPlayerId(Integer playerId);

}
