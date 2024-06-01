package com.jvm.lecti.presentation.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.jvm.lecti.domain.entity.Module;

import com.jvm.lecti.presentation.dto.response.ModuleDto;

@Mapper
public interface ModuleMapper {

   ModuleMapper INSTANCE = Mappers.getMapper(ModuleMapper.class);

   ModuleDto moduleEntityToModuleDto(Module entity, Integer progress);

}
