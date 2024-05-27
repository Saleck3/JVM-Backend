package com.jvm.lecti.domain.service;

import com.jvm.lecti.domain.dao.AppleDAO;
import com.jvm.lecti.domain.dao.PlayerDAO;
import com.jvm.lecti.domain.dao.ResultDAO;
import com.jvm.lecti.domain.dao.UserDAO;
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
   private AppleDAO appleDAO;

   @Autowired
   private PlayerDAO playerDAO;

   @Autowired
   private UserDAO userDAO;

   @Autowired
   private ResultDAO resultDAO;

   public PlayerService(PlayerDAO playerDAO, ResultDAO resultDAO, UserDAO userDAO) {
      this.resultDAO = resultDAO;
      this.playerDAO = playerDAO;
      this.userDAO = userDAO;
   }

   public List<Player> getPlayersByUserId(long userId) {
      return playerDAO.findByUserId(userId);
   }

   public Player getPlayer(long playerId) {
      return playerDAO.getReferenceById(playerId);
   }

   public List<Player> getUserPlayers(long userId) {
      return playerDAO.findByUserId(userId);
   }

   public boolean updatePlayerCrowns(int playerId, int totalCrowns, int spentCrowns) {
      return false;
   }

   public Player addPlayer(PlayerRequest playerReq, User user) {
      Player player = new Player(playerReq.getFirstName(), playerReq.getBirthDate(), user, 0, 0, playerReq.getAlias());
      return playerDAO.save(player);
   }

   public void checkPermissions(String userEmail, long playerId) throws InvalidUserIdForPlayerException {
      Optional<User> user = userDAO.findByEmail(userEmail);
      List<Player> players = playerDAO.findByUserId(user.get().getId());
      boolean playerExists;
      playerExists = players.stream().anyMatch(player -> player.getId() == playerId);
      if (!playerExists) {
         throw new InvalidUserIdForPlayerException();
      }
   }

}
