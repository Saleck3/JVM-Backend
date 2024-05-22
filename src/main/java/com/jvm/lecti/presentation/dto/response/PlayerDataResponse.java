package com.jvm.lecti.presentation.dto.response;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PlayerDataResponse {

   private Long id;

   private String playerName;

   private Date birthDate;

   private Integer totalCrowns;

   private Integer spentCrowns;

   private Integer recomendedModule;

   private String alias;

}