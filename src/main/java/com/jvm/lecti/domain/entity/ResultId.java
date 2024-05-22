package com.jvm.lecti.domain.entity;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ResultId implements Serializable {

   private Long apple_id;
   private Long player_id;

   public Long getApple_id() {
      return apple_id;
   }

   public void setApple_id(Long apple_id) {
      this.apple_id = apple_id;
   }

   public Long getPlayer_id() {
      return player_id;
   }

   public void setPlayer_id(Long player_id) {
      this.player_id = player_id;
   }

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
