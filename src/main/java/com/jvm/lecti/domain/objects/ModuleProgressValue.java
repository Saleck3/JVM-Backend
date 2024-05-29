package com.jvm.lecti.domain.objects;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ModuleProgressValue {

   private Integer id;

   private String description;

   private Integer progress;

}
