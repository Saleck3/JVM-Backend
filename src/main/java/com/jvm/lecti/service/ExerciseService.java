package com.jvm.lecti.service;

import java.util.List;

import com.jvm.lecti.dto.response.ExerciseResponse;
import com.jvm.lecti.entity.Exercise;

public interface ExerciseService {
   List<Exercise> getExercisesByApple(int appleId);
}
