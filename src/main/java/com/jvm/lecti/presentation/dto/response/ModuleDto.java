package com.jvm.lecti.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ModuleDto {

   private Integer id;
   private String description;
   private Integer progress;

}
