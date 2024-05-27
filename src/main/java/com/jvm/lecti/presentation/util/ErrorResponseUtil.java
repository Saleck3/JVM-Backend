package com.jvm.lecti.presentation.util;

import io.jsonwebtoken.Claims;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.jvm.lecti.domain.service.PlayerService;
import com.jvm.lecti.presentation.dto.response.ErrorResponse;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class ErrorResponseUtil {

   @Autowired
   private TokenUtil tokenUtil;

   @Autowired
   private PlayerService playerService;

   public ResponseEntity<ErrorResponse> getErrorResponse(HttpServletRequest request, Integer playerId) {
      try {
         Claims claims = tokenUtil.resolveClaims(request);
         String email = claims.getSubject();
         playerService.checkPermissions(email, playerId);
      } catch (Exception e) {
         ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED, e.getMessage());
         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
      }
      return null;
   }

}
