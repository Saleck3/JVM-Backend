package com.jvm.lecti.service;

import com.jvm.lecti.dto.request.PlayerRequest;
import com.jvm.lecti.dto.response.AppleDto;
import com.jvm.lecti.dto.response.AppleResponse;
import com.jvm.lecti.dto.response.PlayerDto;
import com.jvm.lecti.dto.response.PlayerResponse;
import com.jvm.lecti.entity.Apple;
import com.jvm.lecti.entity.Player;
import com.jvm.lecti.entity.Result;
import com.jvm.lecti.entity.User;
import com.jvm.lecti.exceptions.InvalidUserIdForPlayerException;
import com.jvm.lecti.repository.AppleRepository;
import com.jvm.lecti.repository.PlayerRepository;
import com.jvm.lecti.repository.ResultRepository;
import com.jvm.lecti.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("PlayerService")
public class PlayerServiceImpl implements PlayerService {

   @Autowired
   AppleRepository appleRepository;
   @Autowired
   PlayerRepository playerRepository;

   @Autowired
   UserRepository userRepository;

   @Autowired
   ResultRepository resultRepository;

   public PlayerServiceImpl(PlayerRepository playerRepository, ResultRepository resultRepository, UserRepository userRepository) {
      this.resultRepository = resultRepository;
      this.playerRepository = playerRepository;
      this.userRepository = userRepository;
   }

   public List<Player> getPlayersByUserId(long userId){
      return playerRepository.findByUserId(userId);
   }

   @Override
   public Player getPlayer(long playerId) {
      return playerRepository.getReferenceById(playerId);
   }

   @Override
   public List<Player> getUserPlayers(long userId) {
      return playerRepository.findByUserId(userId);
   }

   @Override
   public boolean updatePlayerCrowns(int playerId, int totalCrowns, int spentCrowns) {
      return false;
   }

   @Override
   public Player addPlayer(PlayerRequest playerReq, User user) {
      Player player = new Player(playerReq.getFirstName(), playerReq.getBirthDate(), user, 0, 0, playerReq.getAlias());
      Player pp = playerRepository.save(player);
      return player;
   }

   @Override
   public void deletePlayer(int id) {

   }

   public void checkPermissions(String userEmail, long playerId) throws InvalidUserIdForPlayerException {
      Optional<User> user = userRepository.findByEmail(userEmail);
      List<Player> players = playerRepository.findByUserId(user.get().getId());
      boolean playerExists;
      playerExists = players.stream().anyMatch(player -> player.getId() == playerId);
      if (!playerExists){
         throw new InvalidUserIdForPlayerException();
      }
   }
}
