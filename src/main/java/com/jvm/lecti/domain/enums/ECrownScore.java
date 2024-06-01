package com.jvm.lecti.domain.enums;

public enum ECrownScore {

   ONE_CROWN(1),
   TWO_CROWN(2),
   THREE_CROWN(3);

   private final int value;

   ECrownScore(int value) {
      this.value = value;
   }

   public int getValue() {
      return value;
   }
}
