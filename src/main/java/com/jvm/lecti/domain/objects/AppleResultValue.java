package com.jvm.lecti.domain.objects;

import com.jvm.lecti.domain.enums.EAppleType;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AppleResultValue {

   private Integer id;

   private String name;

   private Integer crowns;

   private EAppleType appleType;

}
