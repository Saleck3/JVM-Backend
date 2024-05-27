package com.jvm.lecti.domain.dao;

import com.jvm.lecti.domain.entity.Player;

import java.util.List;

public interface PlayerDAO {

    List<Player> findByUserId(Long id);

    int updateCrowns(Integer totalCrowns, Integer spentCrowns);

    Player save(Player player);

    Player getReferenceById(long id);

}
