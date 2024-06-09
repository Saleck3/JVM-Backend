package com.jvm.lecti.domain.objects;

import com.jvm.lecti.domain.enums.AppleType;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AppleResultValue {

   private Integer id;

   private String name;

   private Integer score;

   private AppleType appleType;

}
