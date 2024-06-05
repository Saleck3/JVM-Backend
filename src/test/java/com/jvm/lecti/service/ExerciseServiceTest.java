package com.jvm.lecti.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.jvm.lecti.domain.dao.ExerciseDAO;
import com.jvm.lecti.domain.entity.Exercise;
import com.jvm.lecti.domain.service.ExerciseService;

public class ExerciseServiceTest {

   @Mock
   private ExerciseDAO exerciseDAO;

   @InjectMocks
   private ExerciseService exerciseService;

   @BeforeEach
   public void setUp() {
      MockitoAnnotations.openMocks(this);
      exerciseService = new ExerciseService(exerciseDAO); // Ensure manual injection
   }

   @Test
   public void testGetExercisesByApple() {
      // Arrange
      Integer appleId = 1;
      List<Exercise> mockExercises = Arrays.asList(new Exercise(), new Exercise());
      when(exerciseDAO.findAllByAppleId(appleId)).thenReturn(mockExercises);

      // Act
      List<Exercise> result = exerciseService.getExercisesByApple(appleId);

      // Assert
      assertEquals(mockExercises.size(), result.size());
      verify(exerciseDAO, times(1)).findAllByAppleId(appleId);
   }

}
