package com.jvm.lecti.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.jvm.lecti.domain.enums.EAppleType;
import com.jvm.lecti.domain.objects.AppleResultValue;
import com.jvm.lecti.domain.service.AppleService;
import com.jvm.lecti.presentation.controller.AppleController;
import com.jvm.lecti.presentation.util.ErrorResponseUtil;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;

@SpringBootTest
public class AppleControllerTest {

   @Mock
   private AppleService appleService;

   @Mock
   private ErrorResponseUtil errorResponseUtil;

   @InjectMocks
   private AppleController appleController;

   @Test
   public void testGetApplesFromModuleSuccess() {
      AppleResultValue appleResultValue = AppleResultValue.builder().id(1).name("Apple test").crowns(2).appleType(EAppleType.NO_IA).build();
      HttpServletRequest request = mock(HttpServletRequest.class);
      int playerId = 123;
      int moduleId = 456;
      List<AppleResultValue> mockAppleResultValues = new ArrayList<>();
      mockAppleResultValues.add(appleResultValue);

      when(errorResponseUtil.checkPermissionForUser(request, playerId)).thenReturn(null);
      when(appleService.getAppleResultWithPlayerCrowns(moduleId, playerId)).thenReturn(mockAppleResultValues);

      ResponseEntity responseEntity = appleController.getApplesFromModule(request, moduleId, playerId);

      assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
   }

}



