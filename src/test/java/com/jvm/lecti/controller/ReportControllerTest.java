package com.jvm.lecti.controller;

import com.jvm.lecti.domain.enums.AppleType;
import com.jvm.lecti.domain.objects.AppleResultValue;
import com.jvm.lecti.domain.service.AppleService;
import com.jvm.lecti.domain.service.ReportService;
import com.jvm.lecti.presentation.controller.AppleController;
import com.jvm.lecti.presentation.controller.ReportController;
import com.jvm.lecti.presentation.util.ErrorResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ReportControllerTest {

   @Mock
   private ReportService reportService;

   @Mock
   private ErrorResponseUtil errorResponseUtil;

   @InjectMocks
   private ReportController reportController;

   @Test
   public void testGetReportSuccess() {
      File file = new File("");
      HttpServletRequest request = mock(HttpServletRequest.class);
      int playerId = 123;


      when(reportService.createReport(playerId)).thenReturn(file);

      ResponseEntity responseEntity = reportController.getReport(request, playerId);

      assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
   }

}



