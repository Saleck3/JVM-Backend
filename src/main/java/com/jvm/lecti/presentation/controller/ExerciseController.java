package com.jvm.lecti.presentation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jvm.lecti.domain.exceptions.ApplePlayerNotFoundException;
import com.jvm.lecti.domain.exceptions.InvalidErrorQuantityException;
import com.jvm.lecti.domain.service.ExerciseService;
import com.jvm.lecti.domain.service.ModuleService;
import com.jvm.lecti.domain.service.ScoringService;
import com.jvm.lecti.presentation.dto.request.ScoreRequest;
import com.jvm.lecti.presentation.dto.response.ErrorResponse;
import com.jvm.lecti.presentation.dto.response.ExerciseDto;
import com.jvm.lecti.presentation.dto.response.ExerciseResponse;
import com.jvm.lecti.domain.entity.Exercise;
import com.jvm.lecti.presentation.dto.response.ScoreResponse;
import com.jvm.lecti.presentation.mappers.ExerciseMapper;
import com.jvm.lecti.presentation.util.ErrorResponseUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@RestController
@RequestMapping("/api/exercise")
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseController {

   @Autowired
   private ExerciseService exerciseService;

   @Autowired
   private ModuleService moduleService;

   @Autowired
   private ErrorResponseUtil errorResponseUtil;

   @Autowired
   private ScoringService scoringService;

   @GetMapping("/getExerciseByAppleId")
   public ResponseEntity getExerciseByAppleId(HttpServletRequest httpServletRequest, @NonNull @RequestParam(value = "appleId") Integer appleId,
         @NonNull @RequestParam(value = "playerId") Integer playerId) {
      ResponseEntity<ErrorResponse> errorResponse = errorResponseUtil.checkPermissionForUser(httpServletRequest, playerId);

      if (errorResponse != null) {
         return errorResponse;
      }

      List<Exercise> exercises = exerciseService.getExercisesByApple(appleId);
      List<ExerciseDto> exercisesDto = ExerciseMapper.INSTANCE.exerciseListToExerciseListDto(exercises);
      Integer moduleId = moduleService.obtainModuleIdFromExercise(exercises);

      return ResponseEntity.ok(ExerciseResponse.builder().moduleId(moduleId).exercises(exercisesDto).build());
   }

   @PostMapping("/obtainScore")
   public ResponseEntity generateScoreForPlayer(HttpServletRequest httpServletRequest, @Valid @RequestBody ScoreRequest scoreRequest) {
      ResponseEntity<ErrorResponse> errorResponse = errorResponseUtil.checkPermissionForUser(httpServletRequest, scoreRequest.getPlayerId());

      if (errorResponse != null) {
         return errorResponse;
      }

      try {
         Integer finalScore = scoringService.generateScoreForPlayer(scoreRequest.getPlayerId(), scoreRequest.getAppleId(),
               scoreRequest.getExercises());
         ScoreResponse scoreResponse = ScoreResponse.builder().score(finalScore).build();
         return ResponseEntity.ok(scoreResponse);
      } catch (ApplePlayerNotFoundException | InvalidErrorQuantityException ex) {
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage()));
      }
   }

}
