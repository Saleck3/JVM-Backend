package com.jvm.lecti.presentation.dto.request;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ScoreRequest {

   @NotNull(message = "Missing required parameter: playerId")
   private Integer playerId;

   @NotNull(message = "Missing required parameter: appleId")
   private Integer appleId;

   @NotEmpty(message = "Missing required parameter: exercises")
   private List<Integer> exercises;

}
