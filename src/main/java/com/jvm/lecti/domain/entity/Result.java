package com.jvm.lecti.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "result")
public class Result {

   @EmbeddedId
   private ResultId id;

   @ManyToOne
   @JoinColumn(name = "apple_id", referencedColumnName = "id", insertable = false, updatable = false)
   private Apple apple;

   @ManyToOne
   @JoinColumn(name = "player_id", referencedColumnName = "id", insertable = false, updatable = false)
   private Player player;

   @Column(name = "score")
   private int score;

}
