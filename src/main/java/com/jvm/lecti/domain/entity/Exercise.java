package com.jvm.lecti.domain.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import com.jvm.lecti.domain.enums.ExerciseType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
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

   @Column(name = "exercise_type")
   @Enumerated(EnumType.STRING)
   private ExerciseType exerciseType;

   @Column(name = "parameters")
   private String parameters;

}
