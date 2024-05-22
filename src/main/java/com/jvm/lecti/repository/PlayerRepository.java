package com.jvm.lecti.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.jvm.lecti.domain.entity.Player;
import org.springframework.transaction.annotation.Transactional;

public interface PlayerRepository extends JpaRepository<Player, Long> {

   @Query("SELECT p FROM Player p JOIN p.user u WHERE u.id = :id")
   List<Player> findByUserId(Long id);

   @Transactional
   @Modifying
   @Query("update Player p set p.totalCrowns = ?1, p.spentCrowns = ?2")
   int updateCrowns(Integer totalCrowns, Integer spentCrowns);


}
