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


//   public List<Apple> getApples(int moduleId, int playerId) {
//      List<Apple> apples = appleRepository.findAllByModuleId(moduleId);
//      for (Apple apple : apples) {
//         Optional<Result> result = resultRepository.findAllByAppleAndPlayerId(apple.getId(), playerId);
//         if (result.isPresent()) {
//            apple.setScore(result.get().getScore());
//         } else {
//            apple.setScore(0);
//         }
//      }
//      return apples;
//   }

   public PlayerResponse getPlayersByUserId(long userId){
      List<Player> players = playerRepository.findByUserId(userId);
      PlayerResponse playerResponse = new PlayerResponse(mapModuleDto(players));
      return playerResponse;
   }

   private List<PlayerDto> mapModuleDto(List<Player> players) {
      if (players.isEmpty()) {
         return null;
      }
      List<PlayerDto> playerList = new ArrayList<>();
      for (Player entity : players) {
         playerList.add(new PlayerDto(entity.getId(), entity.getPlayerName(), entity.getBirthDate(), entity.getTotalCrowns(), entity.getSpentCrowns(), entity.getAlias()));
      }
      return playerList;
   }

   @Override
   public PlayerResponse getPlayer(int playerId) {
      return null;
   }

   @Override
   public PlayerResponse getUserPlayers(long userId) {
      List<Player> players = playerRepository.findByUserId(userId);
      PlayerResponse playerResponse = new PlayerResponse(mapModuleDto(players));
      return playerResponse;
   }

   @Override
   public boolean updatePlayerCrowns(int playerId, int totalCrowns, int spentCrowns) {
      return false;
   }

   @Override
   public PlayerDto addPlayer(PlayerRequest playerReq, String userEmail) {
      Optional<User> user = userRepository.findByEmail(userEmail);
      Player player = new Player(playerReq.getFirstName(), playerReq.getBirthDate(), user.get(), 0, 0, playerReq.getAlias());
      Player pp = playerRepository.save(player);
      return new PlayerDto(pp.getId(),player.getPlayerName(),player.getBirthDate(),player.getTotalCrowns(),player.getSpentCrowns(), player.getAlias());
   }

   @Override
   public void deletePlayer(int id) {

   }
}
