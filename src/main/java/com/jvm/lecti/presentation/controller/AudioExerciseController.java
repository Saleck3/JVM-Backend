package com.jvm.lecti.presentation.controller;

import java.io.IOException;

import lombok.NonNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jvm.lecti.domain.objects.AudioExerciseValue;
import com.jvm.lecti.domain.service.AudioExerciseService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping("/api/ia/audio")
@NoArgsConstructor
@AllArgsConstructor
public class AudioExerciseController {

   @Autowired
   private AudioExerciseService audioExerciseService;

   @PostMapping("/checkAudio")
   public ResponseEntity checkAudio(@NonNull @RequestParam(value = "exerciseId", required = false) Integer exerciseId,
         @NonNull @RequestParam("file") MultipartFile file) throws IOException {
      AudioExerciseValue aev = audioExerciseService.checkExercise(file, exerciseId);
      return ResponseEntity.ok(aev);
   }

}
