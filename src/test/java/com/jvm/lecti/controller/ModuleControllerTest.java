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

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.jvm.lecti.domain.entity.Module;
import com.jvm.lecti.domain.service.AppleService;
import com.jvm.lecti.domain.service.ModuleService;
import com.jvm.lecti.domain.service.ScoringService;
import com.jvm.lecti.presentation.controller.ModuleController;
import com.jvm.lecti.presentation.dto.request.ModuleScoreRequest;
import com.jvm.lecti.presentation.dto.response.ModuleRecommendedResponse;
import com.jvm.lecti.presentation.util.ErrorResponseUtil;

import jakarta.servlet.http.HttpServletRequest;

@SpringBootTest
public class ModuleControllerTest {

   @Mock
   private ModuleService moduleService;

   @Mock
   private AppleService appleService;

   @Mock
   private ScoringService scoringService;

   @InjectMocks
   private ModuleController moduleController;

   @BeforeEach
   public void setUp() {
      MockitoAnnotations.openMocks(this);
   }

   @Test
   public void testGetAllModulesSuccess() {
      HttpServletRequest request = mock(HttpServletRequest.class);
      int playerId = 123;
      List<Module> mockModules = new ArrayList<>();
      mockModules.add(new Module());
      when(moduleService.findAllModules()).thenReturn(mockModules);
      when(appleService.getModuleProgressValue(anyInt(), eq(playerId))).thenReturn(50);

      ResponseEntity responseEntity = moduleController.getAllModules(request, playerId);

      assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
   }

   @Test
   public void testGetRecommendedModule() {
      // Mock data
      ModuleScoreRequest request = new ModuleScoreRequest();
      request.setExercises(new ArrayList<>()); // Add exercises as needed

      // Mock behavior
      when(scoringService.obtainRecommendedModule(anyList())).thenReturn(1); // Assuming module id 1 as recommended

      // Call controller method
      ResponseEntity<ModuleRecommendedResponse> responseEntity = moduleController.getRecommendedModule(request);

      // Verify results
      assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
      ModuleRecommendedResponse moduleResponse = responseEntity.getBody();
      assertNotNull(moduleResponse);
      assertEquals(1, moduleResponse.getRecommendedModule());

      // Verify interactions
      verify(scoringService, times(1)).obtainRecommendedModule(anyList());
   }

}

