package com.jvm.lecti.entity;

import java.util.Date;

import com.jvm.lecti.util.Utils;
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

   @Column(name = "total_crowns", nullable = false, columnDefinition = "INTEGER DEFAULT 0")
   private Integer totalCrowns;

   @Column(name = "spent_crowns", nullable = false, columnDefinition = "INTEGER DEFAULT 0")
   private Integer spentCrowns;

   @Column(name = "recommended_module")
   private Integer recommendedModule;

   @Column(name = "alias")
   private String alias;

   @ManyToOne
   @JoinColumn(name = "user_id")
   private User user;

   public Player(){}
   public Player(String name, String birthDate, User user, int totalCrowns, int spentCrowns, String alias){
      this.playerName = name;
      String format = "yyyy-MM-dd";
      this.birthDate = Utils.stringToDate(birthDate, format);
      this.user = user;
      this.totalCrowns = totalCrowns;
      this.spentCrowns = spentCrowns;
      this.alias = alias;
   }

   public String getAlias() {
      return alias;
   }

   public void setAlias(String alias) {
      this.alias = alias;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getPlayerName() {
      return playerName;
   }

   public void setPlayerName(String playerName) {
      this.playerName = playerName;
   }

   public Date getBirthDate() {
      return birthDate;
   }

   public void setBirthDate(Date birthDate) {
      this.birthDate = birthDate;
   }

   public Integer getTotalCrowns() {
      return totalCrowns;
   }

   public void setTotalCrowns(Integer totalCrowns) {
      this.totalCrowns = totalCrowns;
   }

   public Integer getSpentCrowns() {
      return spentCrowns;
   }

   public void setSpentCrowns(Integer spentCrowns) {
      this.spentCrowns = spentCrowns;
   }

   public Integer getRecommendedModule() {
      return recommendedModule;
   }

   public void setRecommendedModule(Integer recommendedModule) {
      this.recommendedModule = recommendedModule;
   }

   public User getUser() {
      return user;
   }

   public void setUser(User user) {
      this.user = user;
   }
}
