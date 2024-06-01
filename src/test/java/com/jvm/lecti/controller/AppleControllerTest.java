package com.jvm.lecti.controller;

import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.springframework.boot.test.context.SpringBootTest;

import com.jvm.lecti.domain.service.AppleService;
import com.jvm.lecti.presentation.controller.AppleController;
import com.jvm.lecti.presentation.util.ErrorResponseUtil;

import jakarta.servlet.http.HttpServletRequest;

@SpringBootTest
public class AppleControllerTest {

   private AppleController appleController;

   private AppleService appleService;

   private HttpServletRequest request;

   private ErrorResponseUtil errorResponseUtil;

   @Before
   public void init() {
      appleService = mock(AppleService.class);
      errorResponseUtil = mock(ErrorResponseUtil.class);
      request = mock(HttpServletRequest.class);
      appleController = new AppleController(appleService, errorResponseUtil);
   }

}


