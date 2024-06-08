package com.jvm.lecti.presentation.dto.request;

import lombok.Data;

@Data
public class AudioExerciseRequest {

   private Integer playerId;

   private Integer exerciseId;

   private String content;

   private String expectedText;

}
