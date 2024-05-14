package com.jvm.lecti.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "result")
public class Result {

   @EmbeddedId
   private ResultId id;

   @ManyToOne
   @JoinColumn(name = "apple_id", referencedColumnName = "id", insertable=false, updatable=false)
   private Apple apple;

   @ManyToOne
   @JoinColumn(name = "player_id", referencedColumnName = "id", insertable=false, updatable=false)
   private Player player;

   @Column(name = "score")
   private int score;

   public Apple getApple() {
      return apple;
   }

   public void setApple(Apple apple) {
      this.apple = apple;
   }

   public Player getPlayer() {
      return player;
   }

   public void setPlayer(Player player) {
      this.player = player;
   }

   public int getScore() {
      return score;
   }

   public void setScore(int score) {
      this.score = score;
   }

}
