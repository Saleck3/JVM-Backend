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

import com.jvm.lecti.domain.entity.Module;
import com.jvm.lecti.domain.service.AppleService;
import com.jvm.lecti.domain.service.ModuleService;
import com.jvm.lecti.presentation.controller.ModuleController;
import com.jvm.lecti.presentation.util.ErrorResponseUtil;

import jakarta.servlet.http.HttpServletRequest;

@SpringBootTest
public class ModuleControllerTest {

   @Mock
   private ModuleService moduleService;

   @Mock
   private AppleService appleService;

   @Mock
   private ErrorResponseUtil errorResponseUtil;

   @InjectMocks
   private ModuleController moduleController;

   @BeforeEach
   public void setUp() {
      MockitoAnnotations.openMocks(this);
   }

   @Test
   public void testGetAllModulesPermissionDenied() {
      HttpServletRequest request = mock(HttpServletRequest.class);
      int playerId = 123;
      when(errorResponseUtil.checkPermissionForUser(request, playerId)).thenReturn(ResponseEntity.status(HttpStatus.FORBIDDEN).body(null));

      ResponseEntity responseEntity = moduleController.getAllModules(request, playerId);

      assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
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

}

