package com.jvm.lecti.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jvm.lecti.entity.Apple;
import com.jvm.lecti.entity.Result;
import com.jvm.lecti.entity.User;

@Repository
public interface ResultRepository extends JpaRepository<Result, Integer> {

   @Query("SELECT r FROM Result r JOIN r.apple a JOIN r.player p WHERE a.id = :appleId AND p.id = :playerId")
   Optional<Result> findAllByAppleAndPlayerId(int appleId, int playerId);

}
