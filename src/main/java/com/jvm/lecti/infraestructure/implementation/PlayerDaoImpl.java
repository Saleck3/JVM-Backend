package com.jvm.lecti.infraestructure.implementation;

import com.jvm.lecti.domain.dao.PlayerDAO;
import com.jvm.lecti.domain.entity.Player;
import com.jvm.lecti.infraestructure.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlayerDaoImpl implements PlayerDAO {

    @Autowired
    PlayerRepository playerRepository;

    public PlayerDaoImpl(PlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }

    @Override
    public List<Player> findByUserId(Long id) {
        return playerRepository.findByUserId(id);
    }

    @Override
    public int updateCrowns(Integer totalCrowns, Integer spentCrowns) {
        return 0;
    }

    @Override
    public Player save(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public Player getReferenceById(long id) {
        return playerRepository.getReferenceById(id);
    }
}
