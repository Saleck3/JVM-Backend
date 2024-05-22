package com.jvm.lecti.domain.service;

import java.util.List;

import com.jvm.lecti.presentation.dto.request.PlayerRequest;
import com.jvm.lecti.domain.entity.Player;
import com.jvm.lecti.domain.entity.User;
import com.jvm.lecti.domain.exceptions.InvalidUserIdForPlayerException;

public interface PlayerService {
   Player getPlayer(long playerId);

   List<Player> getUserPlayers(long userId);

   boolean updatePlayerCrowns(int playerId, int totalCrowns, int spentCrowns);

   Player addPlayer(PlayerRequest player, User user);

   void deletePlayer(int id);

   void checkPermissions(String userEmail, long playerId) throws InvalidUserIdForPlayerException;

   public List<Player> getPlayersByUserId(long userId);
}
