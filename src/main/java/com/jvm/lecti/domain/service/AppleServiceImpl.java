package com.jvm.lecti.domain.service;

import java.util.List;
import java.util.Optional;

import com.jvm.lecti.repository.PlayerRepository;
import com.jvm.lecti.repository.UserRepository;
import com.jvm.lecti.domain.AppleRepository;
import com.jvm.lecti.repository.ResultRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jvm.lecti.domain.entity.Apple;
import com.jvm.lecti.domain.entity.Result;


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

   public AppleServiceImpl(AppleRepository appleRepository, ResultRepository resultRepository, UserRepository userRepository,
         PlayerRepository playerRepository) {
      this.resultRepository = resultRepository;
      this.appleRepository = appleRepository;
      this.userRepository = userRepository;
      this.playerRepository = playerRepository;
   }

   @Override
   public Optional<Apple> getApple(int playerId, int appleId) {

      Optional<Apple> apple = appleRepository.findById(appleId);
      if(apple.isEmpty()) {
         return apple;
      }

      Optional<Result> result = resultRepository.findAllByAppleAndPlayerId(apple.get().getId(), playerId);
      if (result.isPresent()) {
         apple.get().setScore(result.get().getScore());
      } else {
         apple.get().setScore(0);
      }
      return apple;

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

}
