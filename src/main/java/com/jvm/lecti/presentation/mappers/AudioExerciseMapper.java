package com.jvm.lecti.presentation.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.jvm.lecti.domain.entity.Exercise;
import com.jvm.lecti.domain.objects.AudioExerciseValue;
import com.jvm.lecti.presentation.dto.response.AudioExerciseDto;
import com.jvm.lecti.presentation.dto.response.ExerciseDto;

@Mapper
public interface AudioExerciseMapper {

   AudioExerciseMapper INSTANCE = Mappers.getMapper(AudioExerciseMapper.class);

   List<AudioExerciseDto> exerciseListToExerciseListDto(List<AudioExerciseValue> exercise);

}
