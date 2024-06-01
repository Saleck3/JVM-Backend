package com.jvm.lecti.domain.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
   private LocalDateTime birthDate;

   @Column(name = "total_crowns", nullable = false)
   private Integer totalCrowns;

   @Column(name = "spent_crowns", nullable = false)
   private Integer spentCrowns;

   @Column(name = "recommended_module")
   private Integer recommendedModule;

   @Column(name = "alias")
   private String alias;

   @ManyToOne
   @JoinColumn(name = "user_id")
   private User user;

   public Player(String playerName, LocalDateTime birthDate, User user, String alias) {
      this.playerName = playerName;
      this.birthDate = birthDate;
      this.totalCrowns = 0;
      this.spentCrowns = 0;
      this.user = user;
      this.alias = alias;
   }

}
