package com.jvm.lecti.presentation.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.jvm.lecti.domain.service.AppleService;
import com.jvm.lecti.domain.service.PlayerService;
import com.jvm.lecti.presentation.dto.response.AppleDto;
import com.jvm.lecti.presentation.dto.response.ErrorResponse;
import com.jvm.lecti.domain.entity.Apple;
import com.jvm.lecti.exceptions.InvalidUserIdForPlayerException;

import io.jsonwebtoken.Claims;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jvm.lecti.presentation.dto.response.AppleResponse;
import com.jvm.lecti.presentation.util.TokenUtil;

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
   private TokenUtil tokenUtil;

   @Autowired
   private PlayerService playerService;

   @GetMapping("/getApple")
   public ResponseEntity getApple(HttpServletRequest request, @RequestParam(value = "playerId", required = false) Integer playerId,
         @RequestParam(value = "appleId", required = false) Integer appleId) {
      if (playerId == null) {
         ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, "Missing required parameter: playerId");
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
      }
      if (appleId == null) {
         ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, "Missing required parameter: appleId");
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
      }

      Claims claims;
      try {
         claims = tokenUtil.resolveClaims(request);
      } catch (Exception e) {
         ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED, e.getMessage());
         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
      }
      String email = claims.get("sub").toString();
      try {
         playerService.checkPermissions(email, playerId);
      } catch (InvalidUserIdForPlayerException e) {
         ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED, e.getMessage());
         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
      }

      Optional<Apple> apple = appleService.getApple(playerId, appleId);
      //Not found
      if (apple.isEmpty()) {
         ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, "There is no apple with id " + appleId);
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
      }

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
         ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, "Missing required parameter: playerId");
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
      }
      if (moduleId == null) {
         ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, "Missing required parameter: moduleId");
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
      }

      //Returns both apples for rendering the path and scores by apples
      Claims claims = null;
      try {
         claims = tokenUtil.resolveClaims(request);
      } catch (Exception e) {
         ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED, e.getMessage());
         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
      }
      try {
         String email = claims.get("sub").toString();
         playerService.checkPermissions(email, playerId);
      } catch (InvalidUserIdForPlayerException iue) {
         ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED, iue.getMessage());
         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
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