package com.jvm.lecti.domain.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import com.jvm.lecti.domain.enums.EAppleType;

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
@Table(name = "apple")
public class Apple {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;

   @ManyToOne
   @JoinColumn(name = "module_id")
   private Module module;

   @Column(name = "name")
   private String name;

   @Column(name = "index")
   private Integer index;

   @Column(name = "apple_type")
   @Enumerated(EnumType.STRING)
   private EAppleType appleType;

   public Apple(int id, String name) {
      this.id = id;
      this.name = name;
   }

}
