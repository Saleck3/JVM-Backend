package com.jvm.lecti.domain.service;

import java.util.List;

import com.jvm.lecti.domain.dao.ExerciseDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jvm.lecti.domain.entity.Exercise;
import com.jvm.lecti.domain.enums.AppleType;

@Service("ExerciseService")
public class ExerciseService {
   
   @Autowired
   private ExerciseDAO exerciseDAO;

   public ExerciseService(ExerciseDAO exerciseDAO) {
      this.exerciseDAO = exerciseDAO;
   }

   public List<Exercise> getExercisesByApple(Integer appleId) {
      return exerciseDAO.findAllByAppleId(appleId);
   }

   public List<Exercise> getExercisesByAppleType(AppleType appleType) {
      return exerciseDAO.findAllByAppleType(appleType);
   }

}
