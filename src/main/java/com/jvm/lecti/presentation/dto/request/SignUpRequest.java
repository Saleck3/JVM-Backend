package com.jvm.lecti.presentation.dto.request;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class SignUpRequest {

   private String email;

   private String password;

   private String firstName;

   private String lastName;

   private String playerName;

   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
   private LocalDateTime birthDate;

   private String alias;

   private int recommendedModule;

}
