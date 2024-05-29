package com.jvm.lecti.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppleDto {

   private Integer id;

   private String name;

   private Integer crowns; // From 0 to 3

}
