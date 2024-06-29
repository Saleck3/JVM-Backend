package com.jvm.lecti.presentation.controller;

import com.jvm.lecti.domain.annotation.CheckPermission;
import com.jvm.lecti.domain.objects.AppleResultValue;
import com.jvm.lecti.domain.service.AppleService;
import com.jvm.lecti.domain.service.ReportService;
import com.jvm.lecti.presentation.dto.response.AppleResponse;
import com.jvm.lecti.presentation.dto.response.ErrorResponse;
import com.jvm.lecti.presentation.mappers.AppleMapper;
import com.jvm.lecti.presentation.util.ErrorResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/api/report")
@NoArgsConstructor
@AllArgsConstructor
public class ReportController {

   @Autowired
   private ReportService reportService;

   @Autowired
   private ErrorResponseUtil errorResponseUtil;

   @CheckPermission
   @PostMapping("/getReport")
   public ResponseEntity<FileSystemResource> getReport(HttpServletRequest request,
           @NonNull @RequestParam(value = "playerId", required = false) Integer playerId) {

      File fReport = reportService.createReport(playerId);
      if(fReport == null){
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
      FileSystemResource report = new FileSystemResource(fReport);
      HttpHeaders headers = new HttpHeaders();
      headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fReport.getName());
      return ResponseEntity.ok()
              .headers(headers)
              .body(report);
   }

}
