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
   public ExerciseResponse getExercisesByApple(int appleId) {
      ExerciseResponse response = new ExerciseResponse();

      List<Exercise> exercises = exerciseRepository.findAllByAppleId(appleId);
      List<ExerciseDto> exercisesDto = mapModuleDto(exercises);
      response.setExercises(exercisesDto);
      return response;
   }

//   @Override
//   public AppleResponse getApple(int id) {
//      AppleResponse response = new AppleResponse();
//      Optional<Apple> apple = appleRepository.findById(id);
//      List<Apple> apples = new ArrayList<Apple>();
//      apples.add(apple.orElseThrow());
//      List<AppleDto> applesDto = mapModuleDto(apples);
//      response.setApples(applesDto);
//      return response;
//   }

//   @Override
//   public AppleResponse getApplesFromMolude(int moduleId) {
//      AppleResponse response = new AppleResponse();
//
//      List<Apple> apples = appleRepository.findAllByModuleId(moduleId);
//      List<AppleDto> applesDto = mapModuleDto(apples);
//      response.setApples(applesDto);
//      return response;
//   }

   private List<ExerciseDto> mapModuleDto(List<Exercise> exercises) {
      if (exercises.isEmpty()) {
         return null;
      }
      List<ExerciseDto> exerciseDtoList = new ArrayList<>();
      for (Exercise entity : exercises) {
         exerciseDtoList.add(new ExerciseDto(entity.getId(), entity.getName(), entity.getExerciseType(), entity.getParameters()));
      }
      return exerciseDtoList;
   }



}
