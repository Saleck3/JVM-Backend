package com.jvm.lecti.infraestructure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jvm.lecti.domain.entity.Apple;
import com.jvm.lecti.domain.entity.Exercise;
import com.jvm.lecti.domain.enums.AppleType;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {

   @Query("SELECT e FROM Exercise e JOIN e.apple a WHERE a.id = :appleId ORDER BY e.index")
   List<Exercise> findAllByAppleId(Integer appleId);

   @Query("SELECT e FROM Exercise e JOIN e.apple a WHERE a.appleType = :appleType")
   List<Exercise> findByAppleType(AppleType appleType);

}
