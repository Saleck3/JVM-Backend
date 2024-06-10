package com.jvm.lecti.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.jvm.lecti.domain.entity.Exercise;
import com.jvm.lecti.domain.enums.ExerciseType;
import com.jvm.lecti.domain.exceptions.ApplePlayerNotFoundException;
import com.jvm.lecti.domain.exceptions.InvalidErrorQuantityException;
import com.jvm.lecti.domain.service.ExerciseService;
import com.jvm.lecti.domain.service.ModuleService;
import com.jvm.lecti.domain.service.ScoringService;
import com.jvm.lecti.presentation.controller.ExerciseController;
import com.jvm.lecti.presentation.dto.request.ScoreRequest;
import com.jvm.lecti.presentation.util.ErrorResponseUtil;

import jakarta.servlet.http.HttpServletRequest;

@SpringBootTest
public class ExerciseControllerTest {

   @Mock
   private ExerciseService exerciseService;

   @Mock
   private ModuleService moduleService;

   @Mock
   private ErrorResponseUtil errorResponseUtil;

   @Mock
   private ScoringService scoringService;

   @InjectMocks
   private ExerciseController exerciseController;

   @BeforeEach
   public void setUp() {
      MockitoAnnotations.openMocks(this);
   }

   @Test
   public void testGetExerciseByAppleIdSuccess() {
      HttpServletRequest request = mock(HttpServletRequest.class);
      int appleId = 456;
      int playerId = 123;
      List<Exercise> mockExercises = new ArrayList<>();
      Exercise ex = new Exercise();
      ex.setExerciseType(ExerciseType.audio_repeating);
      ex.setParameters("test");
      mockExercises.add(ex);
      when(exerciseService.getExercisesByApple(appleId)).thenReturn(mockExercises);
      when(moduleService.obtainModuleIdFromExercise(mockExercises)).thenReturn(789);

      ResponseEntity responseEntity = exerciseController.getExerciseByAppleId(request, appleId, playerId);

      assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
   }

   @Test
   public void testGenerateScoreForPlayerSuccess() throws InvalidErrorQuantityException, ApplePlayerNotFoundException {
      // Arrange
      HttpServletRequest request = mock(HttpServletRequest.class);
      List<Integer> exercises = List.of(1, 2, 3);
      ScoreRequest scoreRequest = new ScoreRequest(1, 1, exercises);

      when(errorResponseUtil.checkPermissionForUser(request, 1)).thenReturn(null);
      when(scoringService.generateScoreForPlayer(1, 1, exercises)).thenReturn(3);

      ResponseEntity responseEntity = exerciseController.generateScoreForPlayer(request, scoreRequest);

      assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
   }

}

