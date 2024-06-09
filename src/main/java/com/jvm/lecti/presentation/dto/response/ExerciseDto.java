package com.jvm.lecti.presentation.dto.response;

import com.jvm.lecti.domain.enums.ExerciseType;

import lombok.Data;

@Data
public class ExerciseDto {

   private Integer id;

   private ExerciseType exerciseType;

   private String parameters;

}
