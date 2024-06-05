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
import com.jvm.lecti.domain.service.ExerciseService;
import com.jvm.lecti.domain.service.ModuleService;
import com.jvm.lecti.presentation.controller.ExerciseController;
import com.jvm.lecti.presentation.dto.response.ErrorResponse;
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

   @InjectMocks
   private ExerciseController exerciseController;

   @BeforeEach
   public void setUp() {
      MockitoAnnotations.openMocks(this);
   }

   @Test
   public void testGetExerciseByAppleIdMissingAppleId() {
      HttpServletRequest request = mock(HttpServletRequest.class);
      int playerId = 123;

      ResponseEntity responseEntity = exerciseController.getExerciseByAppleId(request, null, playerId);

      assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
      assertEquals("Missing required parameter: appleId", ((ErrorResponse) responseEntity.getBody()).getMessage());
   }

   @Test
   public void testGetExerciseByAppleIdSuccess() {
      HttpServletRequest request = mock(HttpServletRequest.class);
      int appleId = 456;
      int playerId = 123;
      List<Exercise> mockExercises = new ArrayList<>();
      mockExercises.add(new Exercise());
      when(exerciseService.getExercisesByApple(appleId)).thenReturn(mockExercises);
      when(moduleService.obtainModuleIdFromExercise(mockExercises)).thenReturn(789);

      ResponseEntity responseEntity = exerciseController.getExerciseByAppleId(request, appleId, playerId);

      assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
   }

}

