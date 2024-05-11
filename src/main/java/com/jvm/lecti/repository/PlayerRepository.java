package com.jvm.lecti.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jvm.lecti.entity.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {

   @Query("SELECT p FROM Player p JOIN p.user u WHERE u.email = :email")
   Optional<Player> findByUserEmail(String email);

}
