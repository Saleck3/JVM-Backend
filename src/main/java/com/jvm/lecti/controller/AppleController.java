package com.jvm.lecti.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jvm.lecti.dto.response.AppleResponse;
import com.jvm.lecti.entity.Apple;
import com.jvm.lecti.dto.response.AppleDto;
import com.jvm.lecti.service.AppleService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping("/api/apple")

@NoArgsConstructor

public class AppleController {

   @Autowired
   private AppleService appleService;

   public AppleController(AppleService appleService){
      this.appleService = appleService;
   }

   @GetMapping("/getApple/{id}")
   public AppleResponse getApple(@PathVariable Integer appleId) {
      return appleService.getApple(appleId);
   }


   @GetMapping("/getApplesByModuleId")
   public AppleResponse getApplesFromModule(@RequestParam(value = "moduleId") Integer moduleId) {
      AppleResponse apple = appleService.getApplesFromMolude(moduleId);
      // int id = apple.map(Apple::getId).orElse(0);
      return apple;
   }

}
