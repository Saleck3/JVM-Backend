package com.jvm.lecti.domain.service;

import com.jvm.lecti.domain.dao.PlayerDAO;
import com.jvm.lecti.domain.dao.UserDAO;
import com.jvm.lecti.domain.exceptions.UserNotFoundException;
import com.jvm.lecti.domain.entity.Player;
import com.jvm.lecti.domain.entity.User;
import com.jvm.lecti.domain.exceptions.InvalidUserIdForPlayerException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service("PlayerService")
public class PlayerService {

   @Autowired
   private PlayerDAO playerDAO;

   @Autowired
   private UserDAO userDAO;

   public PlayerService(PlayerDAO playerDAO, UserDAO userDAO) {
      this.playerDAO = playerDAO;
      this.userDAO = userDAO;
   }

   public List<Player> getPlayersByUserId(long userId) {
      return playerDAO.findByUserId(userId);
   }

   public List<Player> getUserPlayers(long userId) {
      return playerDAO.findByUserId(userId);
   }

   public void addPlayer(String playerName, LocalDate birthDate, User user) {
      playerDAO.save(new Player(playerName, birthDate, user, playerName));
   }

   public void checkPermissions(String userEmail, long playerId) throws InvalidUserIdForPlayerException, UserNotFoundException {
      Optional<User> user = userDAO.findByEmail(userEmail);
      if (user.isPresent()) {
         List<Player> players = playerDAO.findByUserId(user.get().getId());
         boolean playerExists = players.stream().anyMatch(player -> player.getId() == playerId);
         if (!playerExists) {
            throw new InvalidUserIdForPlayerException();
         }
      } else {
         throw new UserNotFoundException();
      }
   }

}
