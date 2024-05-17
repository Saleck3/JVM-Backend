package com.jvm.lecti.service;

import com.jvm.lecti.dto.request.PlayerRequest;
import com.jvm.lecti.dto.response.ExerciseResponse;
import com.jvm.lecti.dto.response.PlayerDto;
import com.jvm.lecti.dto.response.PlayerResponse;
import com.jvm.lecti.entity.Player;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;

public interface PlayerService {
   PlayerResponse getPlayer(int playerId);

   PlayerResponse getUserPlayers(long userId);

   boolean updatePlayerCrowns(int playerId, int totalCrowns, int spentCrowns);

   PlayerDto addPlayer(PlayerRequest player, String userEmail);

   void deletePlayer(int id);
}
