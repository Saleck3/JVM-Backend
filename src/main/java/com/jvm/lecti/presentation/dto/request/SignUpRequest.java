package com.jvm.lecti.presentation.dto.request;

import lombok.Data;

@Data
public class SignUpRequest {

   private String email;

   private String password;

   private String firstName;

   private String lastName;

}
