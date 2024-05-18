package com.jvm.lecti.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jvm.lecti.dto.response.AppleResponse;
import com.jvm.lecti.dto.response.ErrorResponse;
import com.jvm.lecti.dto.response.ExerciseDto;
import com.jvm.lecti.dto.response.ExerciseResponse;
import com.jvm.lecti.dto.response.ModuleResponse;
import com.jvm.lecti.entity.Exercise;
import com.jvm.lecti.service.AppleService;
import com.jvm.lecti.service.ExerciseService;

import lombok.NoArgsConstructor;

@RestController
@RequestMapping("/api/exercise")

@NoArgsConstructor

public class ExerciseController {

   @Autowired
   private ExerciseService exerciseService;

   public ExerciseController(ExerciseService exerciseService) {
      this.exerciseService = exerciseService;
   }

   @GetMapping("/getExerciseByAppleId")
   public ResponseEntity getExcerciseByAppleId(@RequestParam(value = "appleId", required = false) Integer appleId) {
      if (appleId == null) {
         ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, "Missing required parameter: appleId");
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
      }

      List<Exercise> exercises = exerciseService.getExercisesByApple(appleId);
      List<ExerciseDto> exercisesDto = mapModuleDto(exercises);
      return ResponseEntity.ok(ExerciseResponse.builder().exercises(exercisesDto).build());
   }

   private List<ExerciseDto> mapModuleDto(List<Exercise> exercises) {
      if (exercises.isEmpty()) {
         return null;
      }
      List<ExerciseDto> exerciseDtoList = new ArrayList<>();
      for (Exercise entity : exercises) {
         exerciseDtoList.add(new ExerciseDto(entity.getId(), entity.getName(), entity.getExerciseType(), entity.getParameters()));
      }
      return exerciseDtoList;
   }

}
