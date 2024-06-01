package com.jvm.lecti.domain.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class ResultId implements Serializable {

   private Long apple_id;

   private Long player_id;

   @Override
   public boolean equals(Object o) {
      if (this == o) {
         return true;
      }
      if (!(o instanceof ResultId)) {
         return false;
      }
      ResultId resultId = (ResultId) o;
      return apple_id.equals(resultId.apple_id) && player_id.equals(resultId.player_id);
   }

   @Override
   public int hashCode() {
      return Objects.hash(apple_id, player_id);
   }

}
