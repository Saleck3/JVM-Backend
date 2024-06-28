package com.jvm.lecti.presentation.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

import com.jvm.lecti.presentation.dto.response.ErrorResponse;
import com.jvm.lecti.presentation.util.ErrorResponseUtil;

import jakarta.servlet.http.HttpServletRequest;

@Aspect
@Component
public class PermissionAspect {

   @Autowired
   private ErrorResponseUtil errorResponseUtil;

   @Pointcut("@annotation(com.jvm.lecti.domain.annotation.CheckPermission)")
   public void checkPermissionMethods() {
   }

   @Around("checkPermissionMethods()")
   public Object checkPermission(ProceedingJoinPoint joinPoint) throws Throwable {
      HttpServletRequest request = null;
      Integer playerId = null;

      for (Object arg : joinPoint.getArgs()) {
         if (arg instanceof HttpServletRequest) {
            request = (HttpServletRequest) arg;
         } else if (request.getParameterMap().containsKey("playerId")) {
            playerId = (Integer) arg;
         } else {
            playerId = extractPlayerId(arg);
         }
      }

      if (request != null && playerId != null) {
         ResponseEntity<ErrorResponse> errorResponse = errorResponseUtil.checkPermissionForPlayer(request, playerId);
         if (errorResponse != null) {
            return errorResponse;
         }
      }

      return joinPoint.proceed();
   }

   private Integer extractPlayerId(Object arg) {
      try {
         Field playerIdField = arg.getClass().getDeclaredField("playerId");
         playerIdField.setAccessible(true);
         return (Integer) playerIdField.get(arg);
      } catch (NoSuchFieldException | IllegalAccessException e) {
         // Si el campo no existe o no se puede acceder, simplemente devuelve null
         return null;
      }
   }

}




