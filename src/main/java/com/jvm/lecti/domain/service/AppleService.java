package com.jvm.lecti.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.jvm.lecti.domain.dao.AppleDAO;
import com.jvm.lecti.domain.dao.ResultDAO;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import com.jvm.lecti.domain.entity.Apple;
import com.jvm.lecti.domain.entity.Result;
import com.jvm.lecti.domain.enums.ECrownScore;
import com.jvm.lecti.domain.objects.AppleResultValue;

@Service("AppleService")
@AllArgsConstructor
public class AppleService {

   private AppleDAO appleDAO;

   private ResultDAO resultDAO;

   public List<AppleResultValue> getAppleResultWithPlayerCrowns(Integer moduleId, Integer playerId) {
      List<AppleResultValue> appleResultValueList = new ArrayList<>();
      List<Apple> appleList = appleDAO.findAllByModuleIdOrderByIndex(moduleId);
      if (!appleList.isEmpty()) {
         for (Apple apple : appleList) {
            AppleResultValue appleResultValue = AppleResultValue
                  .builder()
                  .id(apple.getId())
                  .name(apple.getName())
                  .crowns(0)
                  .appleType(apple.getAppleType())
                  .build();
            Optional<Result> resultOptional = resultDAO.findByAppleIdAndPlayerId(apple.getId(), playerId);
            if (resultOptional.isPresent()) {
               Result resultEntity = resultOptional.get();
               appleResultValue.setCrowns(resultEntity.getScore());
            }

            appleResultValueList.add(appleResultValue);
         }
      }
      return appleResultValueList;
   }

   public Integer getModuleProgressValue(Integer moduleId, Integer playerId) {
      List<Integer> appleIds = appleDAO.findAllIdByModuleId(moduleId);
      if (!appleIds.isEmpty()) {
         Integer totalScoreFromPlayer = resultDAO.findTotalScoreByAppleIdAndPlayerId(appleIds, playerId);
         if (totalScoreFromPlayer != null) {
            Integer totalApples = appleIds.size();
            return getProgressFromApples(totalApples, totalScoreFromPlayer);
         }
      }
      return 0;
   }

   private Integer getProgressFromApples(Integer totalApples, Integer totalScoreFromPlayer) {
      Integer totalAppleCrowns = totalApples * ECrownScore.THREE_CROWN.getValue();
      // Verificar si totalAppleCrowns es 0 para evitar división por cero
      if (totalAppleCrowns == 0) {
         return 0;
      }
      // Calcular el porcentaje de progreso
      double progress = (double) totalScoreFromPlayer / totalAppleCrowns * 100;
      // Redondear el resultado y devolverlo como un entero
      return (int) Math.round(progress);
   }

}
