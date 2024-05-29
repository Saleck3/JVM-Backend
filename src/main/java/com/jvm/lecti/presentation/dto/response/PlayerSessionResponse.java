package com.jvm.lecti.presentation.dto.response;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PlayerSessionResponse {

   private Long id;

   private String playerName;

   private LocalDateTime birthDate;

   private Integer totalCrowns;

   private Integer spentCrowns;

   private Integer recommendedModule;

   private String alias;

}