package com.jvm.lecti.presentation.dto.request;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerRequest {

   private String playerName;

   private LocalDate birthDate;

   private String alias;

}
