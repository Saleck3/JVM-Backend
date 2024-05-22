package com.jvm.lecti.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jvm.lecti.domain.entity.Exercise;
import com.jvm.lecti.repository.ExerciseRepository;

@Service("ExerciseService")
public class ExerciseServiceImpl implements ExerciseService {

   @Autowired
   ExerciseRepository exerciseRepository;

   public ExerciseServiceImpl(ExerciseRepository exerciseRepository){
      this.exerciseRepository = exerciseRepository;
   }


   @Override
   public List<Exercise> getExercisesByApple(int appleId) {

       return exerciseRepository.findAllByAppleId(appleId);
   }



}
