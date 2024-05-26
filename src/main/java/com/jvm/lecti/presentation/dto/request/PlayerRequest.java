package com.jvm.lecti.presentation.dto.request;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PlayerRequest {

   private String firstName;

   private LocalDateTime birthDate;

   private String alias;

}
