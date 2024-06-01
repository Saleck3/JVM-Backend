package com.jvm.lecti.presentation.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDto {

   private Long id;

   private String playerName;

   private LocalDateTime birthDate;

   private int totalCrowns;

   private int spentCrowns;

   private int recommendedModule;

   private String alias;

}
