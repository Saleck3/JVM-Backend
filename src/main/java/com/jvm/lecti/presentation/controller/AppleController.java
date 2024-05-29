package com.jvm.lecti.presentation.controller;

import java.util.List;

import com.jvm.lecti.domain.objects.AppleResultValue;
import com.jvm.lecti.domain.service.AppleService;
import com.jvm.lecti.presentation.dto.response.ErrorResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jvm.lecti.presentation.dto.response.AppleResponse;
import com.jvm.lecti.presentation.mappers.AppleMapper;
import com.jvm.lecti.presentation.util.ErrorResponseUtil;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping("/api/apple")
@NoArgsConstructor
@AllArgsConstructor
public class AppleController {

   @Autowired
   private AppleService appleService;

   @Autowired
   private ErrorResponseUtil errorResponseUtil;

   @GetMapping("/getApplesByModuleId")
   public ResponseEntity getApplesFromModule(HttpServletRequest request, @RequestParam(value = "moduleId", required = false) Integer moduleId,
         @RequestParam(value = "playerId", required = false) Integer playerId) {
      if (playerId == null) {
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(HttpStatus.BAD_REQUEST, "Missing required parameter: playerId"));
      }
      if (moduleId == null) {
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(HttpStatus.BAD_REQUEST, "Missing required parameter: moduleId"));
      }
      ResponseEntity<ErrorResponse> errorResponse = errorResponseUtil.checkPermissionForUser(request, playerId);
      if (errorResponse != null) {
         return errorResponse;
      }
      List<AppleResultValue> appleResultValues = appleService.getAppleResultWithPlayerCrowns(moduleId, playerId);
      return ResponseEntity.ok(AppleResponse.builder().apples(AppleMapper.INSTANCE.appleResultListToAppleDtoList(appleResultValues)).build());
   }

}
