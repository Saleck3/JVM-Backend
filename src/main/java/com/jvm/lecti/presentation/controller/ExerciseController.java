package com.jvm.lecti.presentation.controller;

import java.util.List;
import java.util.Optional;

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
   private ModuleService moduleService;

   @Autowired
   private ErrorResponseUtil errorResponseUtil;

   @Autowired
   private ScoringService scoringService;

   @GetMapping("/getExerciseByAppleId")
   public ResponseEntity getExerciseByAppleId(HttpServletRequest httpServletRequest, @RequestParam(value = "appleId") Integer appleId,
         @RequestParam(value = "playerId") Integer playerId) {
      if (appleId == null) {
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(HttpStatus.BAD_REQUEST, "Missing required parameter: appleId"));
      }
      errorResponseUtil.checkPermissionForUser(httpServletRequest, playerId);
      List<Exercise> exercises = exerciseService.getExercisesByApple(appleId);
      List<ExerciseDto> exercisesDto = ExerciseMapper.INSTANCE.exerciseListToExerciseListDto(exercises);
      Integer moduleId = moduleService.obtainModuleIdFromExercise(exercises);

      return ResponseEntity.ok(ExerciseResponse.builder().moduleId(moduleId).exercises(exercisesDto).build());
   }

   @PostMapping("/obtainScore")
   public ResponseEntity generateScoreForPlayer(HttpServletRequest httpServletRequest, @RequestBody ScoreRequest scoreRequest) {
      Optional<ResponseEntity<ErrorResponse>> validationError = validateRequest(scoreRequest);
      if (validationError.isPresent()) {
         return validationError.get();
      }
      errorResponseUtil.checkPermissionForUser(httpServletRequest, scoreRequest.getPlayerId());
      try {
         Integer finalScore = scoringService.generateScoreForPlayer(scoreRequest.getPlayerId(), scoreRequest.getAppleId(),
               scoreRequest.getExercises());
         ScoreResponse scoreResponse = ScoreResponse.builder().score(finalScore).build();
         return ResponseEntity.ok(scoreResponse);
      } catch (ApplePlayerNotFoundException | InvalidErrorQuantityException ex) {
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(HttpStatus.BAD_REQUEST, "Error inside the request"));
      }
   }

   private Optional<ResponseEntity<ErrorResponse>> validateRequest(ScoreRequest scoreRequest) {
      if (scoreRequest.getPlayerId() == null) {
         return Optional.of(
               ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(HttpStatus.BAD_REQUEST, "Missing required parameter: playerId")));
      }
      if (scoreRequest.getAppleId() == null) {
         return Optional.of(
               ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(HttpStatus.BAD_REQUEST, "Missing required parameter: appleId")));
      }
      if (scoreRequest.getExercises() == null || scoreRequest.getExercises().isEmpty()) {
         return Optional.of(ResponseEntity
               .status(HttpStatus.BAD_REQUEST)
               .body(new ErrorResponse(HttpStatus.BAD_REQUEST, "Missing required parameter: exercises")));
      }
      return Optional.empty();
   }

}
