package com.jvm.lecti.domain.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "exercise")
public class Exercise {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;

   @ManyToOne
   @JoinColumn(name = "apple_id")
   private Apple apple;

   @ManyToOne
   @JoinColumn(name = "exercise_type_id")
   private ExerciseType exerciseType;

   private String parameters;

   private String Name;

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getName() {
      return Name;
   }

   public void setName(String name) {
      Name = name;
   }

   public Apple getApple() {
      return apple;
   }

   public void setApple(Apple apple) {
      this.apple = apple;
   }

   public ExerciseType getExerciseType() {
      return exerciseType;
   }

   public void setExerciseType(ExerciseType exerciseType) {
      this.exerciseType = exerciseType;
   }

   public String getParameters() {
      return parameters;
   }

   public void setParameters(String parameters) {
      this.parameters = parameters;
   }

}
