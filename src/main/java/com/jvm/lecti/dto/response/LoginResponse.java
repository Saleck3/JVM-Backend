package com.jvm.lecti.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class LoginResponse {

   private Integer totalCrowns;

   private Integer recommendedModule;

   private String token;

}
