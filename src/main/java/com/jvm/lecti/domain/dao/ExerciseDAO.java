package com.jvm.lecti.domain.dao;

import com.jvm.lecti.domain.entity.Exercise;

import java.util.List;
import java.util.Optional;

public interface ExerciseDAO {

   List<Exercise> findAllByAppleId(Integer appleId);

   Optional<Exercise> findById(Integer exerciseId);

}
