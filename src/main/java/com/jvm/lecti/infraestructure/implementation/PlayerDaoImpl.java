package com.jvm.lecti.infraestructure.implementation;

import com.jvm.lecti.domain.dao.PlayerDAO;
import com.jvm.lecti.domain.entity.Player;
import com.jvm.lecti.infraestructure.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PlayerDaoImpl implements PlayerDAO {

    @Autowired
    private PlayerRepository playerRepository;

    public PlayerDaoImpl(PlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }

    @Override
    public Optional<Player> findById(Long id) {
        return playerRepository.findById(id);
    }

    @Override
    public List<Player> findByUserId(Long id) {
        return playerRepository.findByUserId(id);
    }


    @Override
    public Player save(Player player) {
        return playerRepository.save(player);
    }

}
