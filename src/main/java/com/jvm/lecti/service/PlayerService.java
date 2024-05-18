package com.jvm.lecti.service;

import java.util.List;

import com.jvm.lecti.dto.request.PlayerRequest;
import com.jvm.lecti.dto.response.ExerciseResponse;
import com.jvm.lecti.dto.response.PlayerDto;
import com.jvm.lecti.dto.response.PlayerResponse;
import com.jvm.lecti.entity.Player;
import com.jvm.lecti.entity.User;
import com.jvm.lecti.exceptions.InvalidUserIdForPlayerException;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;

public interface PlayerService {
   Player getPlayer(long playerId);

   List<Player> getUserPlayers(long userId);

   boolean updatePlayerCrowns(int playerId, int totalCrowns, int spentCrowns);

   Player addPlayer(PlayerRequest player, User user);

   void deletePlayer(int id);

   void checkPermissions(String userEmail, long playerId) throws InvalidUserIdForPlayerException;

   public List<Player> getPlayersByUserId(long userId);
}
