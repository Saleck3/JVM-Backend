package com.jvm.lecti.dto.response;

public class AppleDto {

   private Integer id;

   private String name;

//   private int score;

   public AppleDto(int id, String desc){
      this.id = id;
      this.name = desc;
   }

//   public int getScore() {
//      return score;
//   }
//
//   public void setScore(int score) {
//      this.score = score;
//   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

}
