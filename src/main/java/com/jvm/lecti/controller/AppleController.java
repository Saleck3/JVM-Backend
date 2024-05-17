package com.jvm.lecti.controller;

import io.jsonwebtoken.Claims;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jvm.lecti.dto.response.AppleResponse;
import com.jvm.lecti.service.AppleService;
import com.jvm.lecti.util.TokenUtil;

import jakarta.servlet.http.HttpServletRequest;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping("/api/apple")

@NoArgsConstructor

public class AppleController {

   @Autowired
   private AppleService appleService;

   @Autowired
   private TokenUtil tokenUtil;

   public AppleController(AppleService appleService) {
      this.appleService = appleService;
   }

   @GetMapping("/getApple")
   public AppleResponse getApple(@RequestParam(value = "appleId") Integer appleId) {
      return appleService.getApple(appleId);
   }

   @GetMapping("/getApplesByModuleId")
   public AppleResponse getApplesFromModule(HttpServletRequest request, @RequestParam(value = "moduleId") Integer moduleId, @RequestParam(value = "playerId") Integer playerId) {
      //ACA SE ESTA DEVOLVIENDO TANTO LA INFO PARA ARMAR EL CAMINO DE MANZANAS COMO TAMBIEN EL SCORE QUE TIENE EN CADA MANZANA
//      if(checkForPermision())
      AppleResponse apple = appleService.getApplesFromMolude(moduleId, playerId);
      return apple;
   }

   private boolean checkForPermision() {
      return true;
   }

}
