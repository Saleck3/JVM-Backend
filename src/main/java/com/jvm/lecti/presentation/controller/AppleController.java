package com.jvm.lecti.presentation.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.jvm.lecti.domain.service.AppleService;
import com.jvm.lecti.presentation.dto.response.AppleDto;
import com.jvm.lecti.presentation.dto.response.ErrorResponse;
import com.jvm.lecti.domain.entity.Apple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jvm.lecti.presentation.dto.response.AppleResponse;
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

   @GetMapping("/getApple")
   public ResponseEntity getApple(HttpServletRequest request, @RequestParam(value = "playerId", required = false) Integer playerId,
         @RequestParam(value = "appleId", required = false) Integer appleId) {
      if (playerId == null) {
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(HttpStatus.BAD_REQUEST, "Missing required parameter: playerId"));
      }
      if (appleId == null) {
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(HttpStatus.BAD_REQUEST, "Missing required parameter: appleId"));
      }
      ResponseEntity<ErrorResponse> errorResponse = errorResponseUtil.getErrorResponse(request, playerId);
      if (errorResponse != null) {
         return errorResponse;
      }

      Optional<Apple> apple = appleService.getApple(playerId, appleId);
      //Not found
      if (apple.isEmpty()) {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(HttpStatus.NOT_FOUND, "There is no apple with id " + appleId));
      }

      //Modificar
      List<Apple> apples = new ArrayList<>();
      apples.add(apple.orElseThrow());

      List<AppleDto> applesDto = mapModuleDto(apples);
      AppleResponse response = new AppleResponse();
      response.setApples(applesDto);
      return ResponseEntity.ok(response);
   }

   @GetMapping("/getApplesByModuleId")
   public ResponseEntity getApplesFromModule(HttpServletRequest request, @RequestParam(value = "moduleId", required = false) Integer moduleId,
         @RequestParam(value = "playerId", required = false) Integer playerId) {
      if (playerId == null) {
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(HttpStatus.BAD_REQUEST, "Missing required parameter: playerId"));
      }
      if (moduleId == null) {
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(HttpStatus.BAD_REQUEST, "Missing required parameter: moduleId"));
      }
      //Returns both apples for rendering the path and scores by apples
      ResponseEntity<ErrorResponse> errorResponse = errorResponseUtil.getErrorResponse(request, playerId);
      if (errorResponse != null) {
         return errorResponse;
      }
      AppleResponse response = new AppleResponse();
      List<AppleDto> applesDto = mapModuleDto(appleService.getApples(moduleId, playerId));
      response.setApples(applesDto);

      return ResponseEntity.ok(response);
   }

   private List<AppleDto> mapModuleDto(List<Apple> apples) {
      if (apples.isEmpty()) {
         return null;
      }
      List<AppleDto> appleList = new ArrayList<>();
      for (Apple item : apples) {
         int stars;
         if (item.getScore() != 0) {
            int percentage = (item.getScore() * 100) / item.getMax_score();
            if (percentage >= 100) {
               stars = 3;
            } else if (percentage >= 75) {
               stars = 2;
            } else {
               stars = 1;
            }
         } else {
            stars = 0;
         }
         appleList.add(new AppleDto(item.getId(), item.getName(), stars));
      }
      return appleList;
   }

}
