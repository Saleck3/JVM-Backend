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
   @Column(name = "id")
   private Long id;

   @Column(name = "player_name", nullable = false)
   private String playerName;

   @Column(name = "birth_date", nullable = false)
   private Date birthDate;

   @Column(name = "total_crowns", nullable = false)
   private Integer totalCrowns;

   @Column(name = "spent_crowns", nullable = false)
   private Integer spentCrowns;

   @Column(name = "recomended_module")
   private Integer recomendedModule;

   @ManyToOne
   @JoinColumn(name = "user_id")
   private User user;

}
