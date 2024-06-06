package com.jvm.lecti.presentation.dto.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ScoreRequest {

   private Integer playerId;

   private Integer appleId;

   private List<Integer> exercises;

}
