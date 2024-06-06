package com.jvm.lecti.domain.dao;

import com.jvm.lecti.domain.entity.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerDAO {

   Optional<Player> findById(Long id);

   List<Player> findByUserId(Long id);


   Player save(Player player);




}
