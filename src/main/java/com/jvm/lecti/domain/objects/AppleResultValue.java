package com.jvm.lecti.domain.objects;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AppleResultValue {

   private Integer id;

   private String name;

   private Integer crowns;

}
