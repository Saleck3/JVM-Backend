package com.jvm.lecti.presentation.dto.response;

import com.jvm.lecti.domain.entity.ExerciseType;

public class ExerciseDto {

   private Integer id;

   private String name;

   private ExerciseType exerciseType;

   private String params;



//   private int score;

   public ExerciseDto(int id, String desc, ExerciseType exerciseType, String params){
      this.id = id;
      this.name = desc;
      this.exerciseType = exerciseType;
      this.params = params;
   }

//   public int getScore() {
//      return score;
//   }
//
//   public void setScore(int score) {
//      this.score = score;
//   }

   public ExerciseType getExerciseType() {
      return exerciseType;
   }

   public void setExerciseType(ExerciseType exerciseType) {
      this.exerciseType = exerciseType;
   }

   public String getParams() {
      return params;
   }

   public void setParams(String params) {
      this.params = params;
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

}
