package com.jvm.lecti.presentation.dto.request;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModuleScoreRequest {

   @NotEmpty(message = "Missing required parameter: exercises")
   private List<Boolean> exercises;

}
