package com.jvm.lecti.domain.service;

import java.util.List;

import com.jvm.lecti.domain.entity.Exercise;

public interface ExerciseService {
   List<Exercise> getExercisesByApple(int appleId);
}
