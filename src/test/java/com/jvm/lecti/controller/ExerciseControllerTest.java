package com.jvm.lecti.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import com.jvm.lecti.presentation.controller.ExerciseController;

public class ExerciseControllerTest {

   private ExerciseController exerciseController;

   @Before
   public void init() {
      exerciseController = new ExerciseController();
   }

   @Test
   public void returns400OnMissingAppleIdParam() {

      ResponseEntity response = exerciseController.getExcerciseByAppleId(null);
      assertEquals(400, response.getStatusCode().value());
   }

}
