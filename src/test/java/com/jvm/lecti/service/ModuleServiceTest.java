package com.jvm.lecti.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.jvm.lecti.domain.dao.ModuleDAO;
import com.jvm.lecti.domain.entity.Apple;
import com.jvm.lecti.domain.entity.Exercise;
import com.jvm.lecti.domain.entity.Module;
import com.jvm.lecti.domain.service.ModuleService;

public class ModuleServiceTest {

   @Mock
   private ModuleDAO moduleDAO;

   @InjectMocks
   private ModuleService moduleService;

   @BeforeEach
   public void setUp() {
      MockitoAnnotations.openMocks(this);
   }

   @Test
   public void testFindAllModules() {
      List<Module> mockModules = Arrays.asList(new Module(), new Module());
      when(moduleDAO.findAll()).thenReturn(mockModules);

      List<Module> result = moduleService.findAllModules();

      assertEquals(mockModules.size(), result.size());
      verify(moduleDAO, times(1)).findAll();
   }

   @Test
   public void testObtainModuleIdFromExerciseWithExercises() {
      Module module = new Module();
      module.setId(1);
      Apple apple = new Apple();
      apple.setModule(module);
      Exercise exercise = new Exercise();
      exercise.setApple(apple);
      List<Exercise> exercises = Arrays.asList(exercise);

      Integer result = moduleService.obtainModuleIdFromExercise(exercises);

      assertEquals(1, result);
   }

   @Test
   public void testObtainModuleIdFromExerciseEmptyList() {
      List<Exercise> exercises = Collections.emptyList();

      Integer result = moduleService.obtainModuleIdFromExercise(exercises);

      assertNull(result);
   }

}

