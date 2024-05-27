package com.jvm.lecti.domain.dao;

import com.jvm.lecti.domain.entity.Exercise;

import java.util.List;

public interface ExerciseDAO {
    List<Exercise> findAllByAppleId(Integer appleId);
}
