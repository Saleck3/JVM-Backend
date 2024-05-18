package com.jvm.lecti.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jvm.lecti.dto.response.AppleDto;
import com.jvm.lecti.dto.response.AppleResponse;
import com.jvm.lecti.dto.response.ExerciseDto;
import com.jvm.lecti.dto.response.ExerciseResponse;
import com.jvm.lecti.entity.Apple;
import com.jvm.lecti.entity.Exercise;
import com.jvm.lecti.repository.AppleRepository;
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
