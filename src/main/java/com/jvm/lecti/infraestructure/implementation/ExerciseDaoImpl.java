package com.jvm.lecti.infraestructure.implementation;

import com.jvm.lecti.domain.dao.ExerciseDAO;
import com.jvm.lecti.domain.entity.Exercise;
import com.jvm.lecti.infraestructure.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExerciseDaoImpl implements ExerciseDAO {

    @Autowired
    private ExerciseRepository exerciseRepository;

    public ExerciseDaoImpl(ExerciseRepository exerciseRepository){
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public List<Exercise> findAllByAppleId(Integer appleId) {
        return exerciseRepository.findAllByAppleId(appleId);
    }
}
