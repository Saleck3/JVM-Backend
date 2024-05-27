package com.jvm.lecti.presentation.dto.response;

import com.jvm.lecti.domain.entity.ExerciseType;

import lombok.Data;

@Data
public class ExerciseDto {

   private Integer id;

   private String name;

   private ExerciseType exerciseType;

   private String params;

   //   private int score;

   public ExerciseDto(int id, String desc, ExerciseType exerciseType, String params) {
      this.id = id;
      this.name = desc;
      this.exerciseType = exerciseType;
      this.params = params;
   }

}
