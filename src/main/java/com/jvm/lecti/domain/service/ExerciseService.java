package com.jvm.lecti.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jvm.lecti.domain.entity.Exercise;
import com.jvm.lecti.infraestructure.repository.ExerciseRepository;

@Service("ExerciseService")
public class ExerciseService {

   @Autowired
   private ExerciseRepository exerciseRepository;

   public ExerciseService(ExerciseRepository exerciseRepository) {
      this.exerciseRepository = exerciseRepository;
   }

   public List<Exercise> getExercisesByApple(Integer appleId) {
      return exerciseRepository.findAllByAppleId(appleId);
   }

}
