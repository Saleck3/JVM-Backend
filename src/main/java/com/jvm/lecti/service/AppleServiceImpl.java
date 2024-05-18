package com.jvm.lecti.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.jvm.lecti.entity.Player;
import com.jvm.lecti.entity.User;
import com.jvm.lecti.exceptions.InvalidUserIdForPlayerException;
import com.jvm.lecti.repository.PlayerRepository;
import com.jvm.lecti.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jvm.lecti.dto.response.AppleDto;
import com.jvm.lecti.dto.response.AppleResponse;
import com.jvm.lecti.entity.Apple;
import com.jvm.lecti.entity.Result;
import com.jvm.lecti.repository.AppleRepository;
import com.jvm.lecti.repository.ResultRepository;

@Service("AppleService")
public class AppleServiceImpl implements AppleService {

   @Autowired
   AppleRepository appleRepository;

   @Autowired
   ResultRepository resultRepository;

   @Autowired
   UserRepository userRepository;

   @Autowired
   PlayerRepository playerRepository;

   public AppleServiceImpl(AppleRepository appleRepository, ResultRepository resultRepository, UserRepository userRepository, PlayerRepository playerRepository) {
      this.resultRepository = resultRepository;
      this.appleRepository = appleRepository;
      this.userRepository = userRepository;
      this.playerRepository = playerRepository;
   }

   @Override
   public AppleResponse getApple(int id) {
      AppleResponse response = new AppleResponse();
      Optional<Apple> apple = appleRepository.findById(id);
      Optional<Result> result = resultRepository.findAllByAppleAndPlayerId(apple.get().getId(), 1);
      if(result.isPresent()) {
         apple.get().setScore(result.get().getScore());
      }else {
         apple.get().setScore(0);
      }

      List<Apple> apples = new ArrayList<Apple>();
      apples.add(apple.orElseThrow());

      List<AppleDto> applesDto = mapModuleDto(apples);
      response.setApples(applesDto);
      return response;
   }

   @Override
   //TODO: Llevarselo al controller
   public AppleResponse getApplesFromMolude(int moduleId, int playerId, String userEmail) throws InvalidUserIdForPlayerException{

      checkPermissions(userEmail, playerId);
      AppleResponse response = new AppleResponse();

      List<Apple> apples = getApples(moduleId, playerId);

      List<AppleDto> applesDto = mapModuleDto(apples);
      response.setApples(applesDto);

      return response;
   }

   private void checkPermissions(String userEmail, long playerId) throws InvalidUserIdForPlayerException{
      Optional<User> user = userRepository.findByEmail(userEmail);
      List<Player> players = playerRepository.findByUserId(user.get().getId());
      boolean playerExists;
      playerExists = players.stream().anyMatch(player -> player.getId() == playerId);
      if (!playerExists){
         throw new InvalidUserIdForPlayerException();
      }
   }

   public List<Apple> getApples(int moduleId, int playerId) {
      List<Apple> apples = appleRepository.findAllByModuleId(moduleId);
      for (Apple apple : apples) {
         Optional<Result> result = resultRepository.findAllByAppleAndPlayerId(apple.getId(), playerId);
         if (result.isPresent()) {
            apple.setScore(result.get().getScore());
         } else {
            apple.setScore(0);
         }
      }
      return apples;
   }

   private List<AppleDto> mapModuleDto(List<Apple> apples) {
      if (apples.isEmpty()) {
         return null;
      }
      List<AppleDto> appleList = new ArrayList<>();
      for (Apple entity : apples) {
         appleList.add(new AppleDto(entity.getId(), entity.getName()));
      }
      return appleList;
   }

}
