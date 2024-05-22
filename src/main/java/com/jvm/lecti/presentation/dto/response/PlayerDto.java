package com.jvm.lecti.presentation.dto.response;

import java.util.Date;

public class PlayerDto {

   private Long id;

   private String firstName;

   private Date birthDate;

   private int totalCrowns;

   private int spentCrowns;

   private int recommendedModule;

   private String alias;

//   private int score;

   public PlayerDto(long id, String firstName, Date birthDate){
      this.id = id;
      this.firstName = firstName;
      this.birthDate = birthDate;
   }

   public PlayerDto(long id, String firstName, Date birthDate, int totalCrowns, int spentCrowns, String alias){
      this.id = id;
      this.firstName = firstName;
      this.birthDate = birthDate;
      this.totalCrowns = totalCrowns;
      this.spentCrowns = spentCrowns;
      this.alias = alias;
   }

//   public int getScore() {
//      return score;
//   }
//
//   public void setScore(int score) {
//      this.score = score;
//   }


   public Date getBirthDate() {
      return birthDate;
   }

   public void setBirthDate(Date birthDate) {
      this.birthDate = birthDate;
   }

   public int getTotalCrowns() {
      return totalCrowns;
   }

   public void setTotalCrowns(int totalCrowns) {
      this.totalCrowns = totalCrowns;
   }

   public int getSpentCrowns() {
      return spentCrowns;
   }

   public void setSpentCrowns(int spentCrowns) {
      this.spentCrowns = spentCrowns;
   }

   public int getRecommendedModule() {
      return recommendedModule;
   }

   public void setRecommendedModule(int recommendedModule) {
      this.recommendedModule = recommendedModule;
   }

   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getAlias() {
      return alias;
   }

   public void setAlias(String alias) {
      this.alias = alias;
   }
}
