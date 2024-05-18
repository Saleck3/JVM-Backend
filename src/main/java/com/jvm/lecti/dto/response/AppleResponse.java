package com.jvm.lecti.dto.response;

import lombok.Builder;

import java.util.List;


public class AppleResponse {

   List<AppleDto> apples;

   public List<AppleDto> getApples() {
      return apples;
   }

   public void setApples(List<AppleDto> apples) {
      this.apples = apples;
   }

}
