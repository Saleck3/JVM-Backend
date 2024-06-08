package com.jvm.lecti.presentation.controller;

import java.util.List;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jvm.lecti.domain.objects.AppleResultValue;
import com.jvm.lecti.domain.objects.AudioExerciseValue;
import com.jvm.lecti.domain.service.AppleService;
import com.jvm.lecti.domain.service.AudioExerciseService;
import com.jvm.lecti.presentation.dto.request.AudioExerciseRequest;
import com.jvm.lecti.presentation.dto.response.AppleResponse;
import com.jvm.lecti.presentation.dto.response.AudioExerciseDto;
import com.jvm.lecti.presentation.dto.response.ErrorResponse;
import com.jvm.lecti.presentation.mappers.AppleMapper;
import com.jvm.lecti.presentation.util.ErrorResponseUtil;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping("/api/ia/audio")
@NoArgsConstructor
@AllArgsConstructor
public class AudioExerciseController {

   @Autowired
   private AudioExerciseService audioExerciseService;

   @Autowired
   private ErrorResponseUtil errorResponseUtil;

   public AudioExerciseController(AudioExerciseService audioExerciseService){
      this.audioExerciseService = audioExerciseService;
   }

   @PostMapping("/checkAudio")
   public ResponseEntity checkAudio(HttpServletRequest request,@NonNull @RequestParam(value = "exerciseId", required = false) Integer exerciseId,
         @NonNull @RequestParam("file") MultipartFile file) {

      AudioExerciseValue aev = audioExerciseService.checkExercise(file, exerciseId);
      return ResponseEntity.ok(aev);
   }

}
