package com.jvm.lecti.presentation.dto.request;

import java.time.LocalDate;

import lombok.Data;

@Data
public class SignUpRequest {

   private String email;

   private String password;

   private String firstName;

   private String lastName;

   private String playerName;

   private LocalDate birthDate;

   private String alias;

   private int recommendedModule;

}
