package com.jvm.lecti.dto.response;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModuleDto {
   private Integer id;
   private String description;
}
