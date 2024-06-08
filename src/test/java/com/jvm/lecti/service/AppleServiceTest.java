package com.jvm.lecti.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.jvm.lecti.domain.dao.AppleDAO;
import com.jvm.lecti.domain.dao.ResultDAO;
import com.jvm.lecti.domain.entity.Apple;
import com.jvm.lecti.domain.entity.Result;
import com.jvm.lecti.domain.objects.AppleResultValue;
import com.jvm.lecti.domain.service.AppleService;

public class AppleServiceTest {

   @Mock
   private AppleDAO appleDAO;

   @Mock
   private ResultDAO resultDAO;

   @InjectMocks
   private AppleService appleService;

   @BeforeEach
   public void setUp() {
      MockitoAnnotations.openMocks(this);
   }

   @Test
   public void testGetAppleResultWithPlayerCrowns() {
      Integer moduleId = 1;
      Integer playerId = 1;
      List<Apple> appleList = Arrays.asList(new Apple(1, "First Apple"), new Apple(2, "Second Apple"));
      when(appleDAO.findAllByModuleIdOrderByIndex(moduleId)).thenReturn(appleList);

      Result result = new Result();
      result.setScore(5);
      when(resultDAO.findByAppleIdAndPlayerId(1, playerId)).thenReturn(Optional.of(result));
      when(resultDAO.findByAppleIdAndPlayerId(2, playerId)).thenReturn(Optional.empty());

      List<AppleResultValue> resultValues = appleService.getAppleResultWithPlayerCrowns(moduleId, playerId);

      assertEquals(2, resultValues.size());
      assertEquals(5, resultValues.get(0).getScore());
      assertEquals(0, resultValues.get(1).getScore());
      verify(appleDAO, times(1)).findAllByModuleIdOrderByIndex(moduleId);
      verify(resultDAO, times(1)).findByAppleIdAndPlayerId(1, playerId);
      verify(resultDAO, times(1)).findByAppleIdAndPlayerId(2, playerId);
   }

   @Test
   public void testGetModuleProgressValue() {
      Integer moduleId = 1;
      Integer playerId = 1;
      List<Integer> appleIds = Arrays.asList(1, 2, 3);
      when(appleDAO.findAllIdByModuleId(moduleId)).thenReturn(appleIds);
      when(resultDAO.findTotalScoreByAppleIdAndPlayerId(appleIds, playerId)).thenReturn(9);

      Integer progressValue = appleService.getModuleProgressValue(moduleId, playerId);

      assertEquals(100, progressValue);
      verify(appleDAO, times(1)).findAllIdByModuleId(moduleId);
      verify(resultDAO, times(1)).findTotalScoreByAppleIdAndPlayerId(appleIds, playerId);
   }

   @Test
   public void testGetModuleProgressValue_NoApples() {
      Integer moduleId = 1;
      Integer playerId = 1;
      when(appleDAO.findAllIdByModuleId(moduleId)).thenReturn(new ArrayList<>());

      Integer progressValue = appleService.getModuleProgressValue(moduleId, playerId);

      assertEquals(0, progressValue);
      verify(appleDAO, times(1)).findAllIdByModuleId(moduleId);
      verify(resultDAO, times(0)).findTotalScoreByAppleIdAndPlayerId(anyList(), eq(playerId));
   }

}

