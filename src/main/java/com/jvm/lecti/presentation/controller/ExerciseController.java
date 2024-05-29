package com.jvm.lecti.presentation.controller;

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
import com.jvm.lecti.presentation.mappers.ExerciseMapper;
import com.jvm.lecti.presentation.util.ErrorResponseUtil;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping("/api/exercise")
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseController {

   @Autowired
   private ExerciseService exerciseService;

   @Autowired
   private ErrorResponseUtil errorResponseUtil;

   @GetMapping("/getExerciseByAppleId")
   public ResponseEntity getExerciseByAppleId(HttpServletRequest httpServletRequest, @RequestParam(value = "appleId") Integer appleId,
         @RequestParam(value = "playerId") Integer playerId) {
      if (appleId == null) {
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(HttpStatus.BAD_REQUEST, "Missing required parameter: appleId"));
      }
      errorResponseUtil.checkPermissionForUser(httpServletRequest, appleId);
      List<Exercise> exercises = exerciseService.getExercisesByApple(playerId);
      List<ExerciseDto> exercisesDto = ExerciseMapper.INSTANCE.exerciseListToExerciseListDto(exercises);
      return ResponseEntity.ok(ExerciseResponse.builder().exercises(exercisesDto).build());
   }

}
