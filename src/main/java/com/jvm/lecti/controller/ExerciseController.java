package com.jvm.lecti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jvm.lecti.dto.response.AppleResponse;
import com.jvm.lecti.dto.response.ExerciseResponse;
import com.jvm.lecti.service.AppleService;
import com.jvm.lecti.service.ExerciseService;

import lombok.NoArgsConstructor;

@RestController
@RequestMapping("/api/exercise")

@NoArgsConstructor

public class ExerciseController {

   @Autowired
   private ExerciseService exerciseService;

   public ExerciseController(ExerciseService exerciseService){
      this.exerciseService = exerciseService;
   }

//   @GetMapping("/getExercise/{id}")
//   public AppleResponse getApple(@PathVariable Integer appleId) {
//      return exerciseService.get(appleId);
//   }


   @GetMapping("/getExerciseByAppleId")
   public ExerciseResponse getApplesFromModule(@RequestParam(value = "appleId") Integer appleId) {
      ExerciseResponse exerciseResponse = exerciseService.getExercisesByApple(appleId);
      // int id = apple.map(Apple::getId).orElse(0);
      return exerciseResponse;
   }

}
