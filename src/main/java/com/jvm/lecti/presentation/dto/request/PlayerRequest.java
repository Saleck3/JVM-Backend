package com.jvm.lecti.presentation.dto.request;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PlayerRequest {

   private String playerName;

   private LocalDate birthDate;

   private String alias;

}
