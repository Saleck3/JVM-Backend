package com.jvm.lecti.presentation.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.jvm.lecti.domain.entity.Exercise;
import com.jvm.lecti.presentation.dto.response.ExerciseDto;

@Mapper
public interface ExerciseMapper {

   ExerciseMapper INSTANCE = Mappers.getMapper(ExerciseMapper.class);

   List<ExerciseDto> exerciseListToExerciseListDto(List<Exercise> exercise);

}
