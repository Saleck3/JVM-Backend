package com.jvm.lecti.dto.response;

import java.util.List;

public class ExerciseResponse {

   List<ExerciseDto> exercises;

   public List<ExerciseDto> getExercises() {
      return exercises;
   }

   public void setExercises(List<ExerciseDto> exercises) {
      this.exercises = exercises;
   }

}
