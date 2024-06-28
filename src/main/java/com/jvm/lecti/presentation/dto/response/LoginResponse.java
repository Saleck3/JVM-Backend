package com.jvm.lecti.presentation.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class LoginResponse {

   private List<PlayerSessionResponse> players;

   private String token;

   //AVISAR
   private String errorMessage;

}
