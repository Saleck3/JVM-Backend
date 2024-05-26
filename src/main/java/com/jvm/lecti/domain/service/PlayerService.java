package com.jvm.lecti.domain.service;

import com.jvm.lecti.presentation.dto.request.PlayerRequest;
import com.jvm.lecti.domain.entity.Player;
import com.jvm.lecti.domain.entity.User;
import com.jvm.lecti.exceptions.InvalidUserIdForPlayerException;
import com.jvm.lecti.infraestructure.repository.AppleRepository;
import com.jvm.lecti.infraestructure.repository.PlayerRepository;
import com.jvm.lecti.infraestructure.repository.ResultRepository;
import com.jvm.lecti.infraestructure.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("PlayerService")
public class PlayerService {

   @Autowired
   private AppleRepository appleRepository;

   @Autowired
   private PlayerRepository playerRepository;

   @Autowired
   private UserRepository userRepository;

   @Autowired
   private ResultRepository resultRepository;

   public PlayerService(PlayerRepository playerRepository, ResultRepository resultRepository, UserRepository userRepository) {
      this.resultRepository = resultRepository;
      this.playerRepository = playerRepository;
      this.userRepository = userRepository;
   }

   public List<Player> getPlayersByUserId(long userId) {
      return playerRepository.findByUserId(userId);
   }

   public Player getPlayer(long playerId) {
      return playerRepository.getReferenceById(playerId);
   }

   public List<Player> getUserPlayers(long userId) {
      return playerRepository.findByUserId(userId);
   }

   public boolean updatePlayerCrowns(int playerId, int totalCrowns, int spentCrowns) {
      return false;
   }

   public Player addPlayer(PlayerRequest playerReq, User user) {
      Player player = new Player(playerReq.getFirstName(), playerReq.getBirthDate(), user, 0, 0, playerReq.getAlias());
      return playerRepository.save(player);
   }

   public void checkPermissions(String userEmail, long playerId) throws InvalidUserIdForPlayerException {
      Optional<User> user = userRepository.findByEmail(userEmail);
      List<Player> players = playerRepository.findByUserId(user.get().getId());
      boolean playerExists;
      playerExists = players.stream().anyMatch(player -> player.getId() == playerId);
      if (!playerExists) {
         throw new InvalidUserIdForPlayerException();
      }
   }

}
