package com.jvm.lecti.domain.service;

import java.util.List;
import java.util.Optional;

import com.jvm.lecti.domain.dao.AppleDAO;
import com.jvm.lecti.domain.dao.PlayerDAO;
import com.jvm.lecti.domain.dao.ResultDAO;
import com.jvm.lecti.domain.dao.UserDAO;
import com.jvm.lecti.infraestructure.implementation.UserDaoImpl;
import com.jvm.lecti.infraestructure.repository.PlayerRepository;
import com.jvm.lecti.infraestructure.repository.UserRepository;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jvm.lecti.domain.entity.Apple;
import com.jvm.lecti.domain.entity.Result;
import com.jvm.lecti.infraestructure.repository.AppleRepository;
import com.jvm.lecti.infraestructure.repository.ResultRepository;

@Service("AppleService")
@AllArgsConstructor
public class AppleService {

   private AppleDAO appleDAO;

   private ResultDAO resultDAO;

   public Optional<Apple> getApple(int playerId, int appleId) {

      Optional<Apple> apple = appleDAO.findById(appleId);
      if (apple.isEmpty()) {
         return apple;
      }

      Optional<Result> result = resultDAO.findAllByAppleAndPlayerId(apple.get().getId(), playerId);
      if (result.isPresent()) {
         apple.get().setScore(result.get().getScore());
      } else {
         apple.get().setScore(0);
      }
      return apple;

   }

   public List<Apple> getApples(int moduleId, int playerId) {
      List<Apple> apples = appleDAO.findAllByModuleId(moduleId);
      for (Apple apple : apples) {
         Optional<Result> result = resultDAO.findAllByAppleAndPlayerId(apple.getId(), playerId);
         if (result.isPresent()) {
            apple.setScore(result.get().getScore());
         } else {
            apple.setScore(0);
         }
      }
      return apples;
   }

}
