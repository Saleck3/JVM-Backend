package com.jvm.lecti.presentation.dto.response;

public class AppleDto {

   private Integer id;

   private String name;

   private int stars; // From 0 to 3

   public AppleDto(int id, String desc){
      this.id = id;
      this.name = desc;
   }


   public void setStars(int stars) {
      this.stars = stars;
   }

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
