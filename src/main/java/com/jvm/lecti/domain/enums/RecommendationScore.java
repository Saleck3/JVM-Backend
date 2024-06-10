package com.jvm.lecti.domain.enums;

public enum RecommendationScore {

   MODULE_RECOMMENDATION_ADVANCED(3),
   MODULE_RECOMMENDATION_INTERMEDIATE(2),
   MODULE_RECOMMENDATION_BASIC(1);

   private final int value;

   RecommendationScore(int value) {
      this.value = value;
   }

   public int getValue() {
      return value;
   }
}
