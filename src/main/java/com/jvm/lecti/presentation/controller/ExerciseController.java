package com.jvm.lecti.presentation.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jvm.lecti.domain.service.ExerciseService;
import com.jvm.lecti.presentation.dto.response.ErrorResponse;
import com.jvm.lecti.presentation.dto.response.ExerciseDto;
import com.jvm.lecti.presentation.dto.response.ExerciseResponse;
import com.jvm.lecti.domain.entity.Exercise;

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
   public ResponseEntity getExerciseByAppleId(@RequestParam(value = "appleId", required = false) Integer appleId) {
      if (appleId == null) {
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(HttpStatus.BAD_REQUEST, "Missing required parameter: appleId"));
      }
      //Falta ver la session
      List<Exercise> exercises = exerciseService.getExercisesByApple(appleId);
      List<ExerciseDto> exercisesDto = mapModuleDto(exercises);
      return ResponseEntity.ok(ExerciseResponse.builder().exercises(exercisesDto).build());
   }

   //Crear mapper
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
