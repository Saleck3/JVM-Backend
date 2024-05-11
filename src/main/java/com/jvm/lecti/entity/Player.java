package com.jvm.lecti.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "player")
public class Player {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "ID")
   private Long id;

   @Column(name = "PLAYER_NAME", nullable = false)
   private String playerName;

   @Column(name = "BIRTH_DATE", nullable = false)
   private Date birthDate;

   @Column(name = "TOTAL_CROWNS", nullable = false)
   private Integer totalCrowns;

   @Column(name = "SPENT_CROWNS", nullable = false)
   private Integer spentCrowns;

   @Column(name = "RECOMENDED_MODULE")
   private Integer recomendedModule;

   @ManyToOne
   @JoinColumn(name = "USER_ID")
   private User user;

}
