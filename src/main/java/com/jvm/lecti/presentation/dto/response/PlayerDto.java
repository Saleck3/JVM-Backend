package com.jvm.lecti.presentation.dto.response;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PlayerDto {

   private Long id;

   private String firstName;

   private LocalDateTime birthDate;

   private int totalCrowns;

   private int spentCrowns;

   private int recommendedModule;

   private String alias;

   public PlayerDto(long id, String firstName, LocalDateTime birthDate, int totalCrowns, int spentCrowns, String alias) {
      this.id = id;
      this.firstName = firstName;
      this.birthDate = birthDate;
      this.totalCrowns = totalCrowns;
      this.spentCrowns = spentCrowns;
      this.alias = alias;
   }

}
