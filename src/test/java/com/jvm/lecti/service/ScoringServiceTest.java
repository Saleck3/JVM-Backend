package com.jvm.lecti.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.jvm.lecti.domain.dao.AppleDAO;
import com.jvm.lecti.domain.dao.PlayerDAO;
import com.jvm.lecti.domain.dao.ResultDAO;
import com.jvm.lecti.domain.entity.Apple;
import com.jvm.lecti.domain.entity.Player;
import com.jvm.lecti.domain.exceptions.ApplePlayerNotFoundException;
import com.jvm.lecti.domain.exceptions.InvalidErrorQuantityException;
import com.jvm.lecti.domain.service.ScoringService;

public class ScoringServiceTest {

   @Mock
   private ResultDAO resultDAO;

   @Mock
   private PlayerDAO playerDAO;

   @Mock
   private AppleDAO appleDAO;

   @InjectMocks
   private ScoringService scoringService;

   @BeforeEach
   public void setUp() {
      MockitoAnnotations.openMocks(this);
   }

   @Test
   public void testGenerateScoreForPlayerSuccess() throws ApplePlayerNotFoundException, InvalidErrorQuantityException {
      Integer playerId = 1;
      Integer appleId = 1;
      List<Integer> exercises = Arrays.asList(0, 1, 2);

      Player player = new Player();
      player.setId(1L);
      Apple apple = new Apple();
      apple.setId(1);
      when(playerDAO.findById(1L)).thenReturn(Optional.of(player));
      when(appleDAO.findById(1)).thenReturn(Optional.of(apple));

      Integer finalScore = scoringService.generateScoreForPlayer(playerId, appleId, exercises);

      assertNotNull(finalScore);
   }

   @Test
   public void testGenerateScoreForPlayerThrowsInvalidErrorQuantityException() {
      Integer playerId = 1;
      Integer appleId = 1;
      List<Integer> exercises = Arrays.asList(0, 1, 6);

      assertThrows(InvalidErrorQuantityException.class, () -> {
         scoringService.generateScoreForPlayer(playerId, appleId, exercises);
      });
   }

   @Test
   public void testGenerateScoreForPlayerThrowsApplePlayerNotFoundException() {
      Integer playerId = 1;
      Integer appleId = 1;
      List<Integer> exercises = Arrays.asList(0, 1, 2);
      when(playerDAO.findById(1L)).thenReturn(Optional.empty());
      when(appleDAO.findById(1)).thenReturn(Optional.empty());

      assertThrows(ApplePlayerNotFoundException.class, () -> {
         scoringService.generateScoreForPlayer(playerId, appleId, exercises);
      });
   }

}
