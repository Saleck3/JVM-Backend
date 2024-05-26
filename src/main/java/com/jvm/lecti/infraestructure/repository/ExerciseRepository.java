package com.jvm.lecti.infraestructure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jvm.lecti.domain.entity.Apple;
import com.jvm.lecti.domain.entity.Exercise;

@Repository
public interface ExerciseRepository extends JpaRepository<Apple, Integer> {

   @Query("SELECT e FROM Exercise e JOIN e.apple a JOIN e.exerciseType WHERE a.id = :appleId")
   List<Exercise> findAllByAppleId(Integer appleId);
}
