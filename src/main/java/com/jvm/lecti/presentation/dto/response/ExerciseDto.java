package com.jvm.lecti.presentation.dto.response;

import com.jvm.lecti.domain.enums.EExerciseType;

import lombok.Data;

@Data
public class ExerciseDto {

   private Integer id;

   private EExerciseType exerciseType;

   private String parameters;

}
