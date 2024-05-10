package com.jvm.lecti.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jvm.lecti.dto.ModuleResponseDto;
import com.jvm.lecti.entity.ModuleEntity;
import com.jvm.lecti.repository.ModuleRepository;

@Service("ModuleService")

public class ModuleServiceImpl implements ModuleService {

   @Autowired
   private ModuleRepository moduleRepository;

   @Override
   public List<ModuleResponseDto> getModulesByUserId(Integer id) {

      var modules = moduleRepository.findAllById(id);
      var modulesDto = getModuleResponseDto(modules);

      return modulesDto;
   }

   private List<ModuleResponseDto> getModuleResponseDto(List<ModuleEntity> modules) {

      List<ModuleResponseDto> moduleResponseDtos = new ArrayList<ModuleResponseDto>();
      modules.forEach(moduleEntity -> {
         var moduleResponseDto = new ModuleResponseDto();
         moduleResponseDto.setId(moduleEntity.getId());
         moduleResponseDto.setDescription(moduleEntity.getDescription());

         moduleResponseDtos.add(moduleResponseDto);
      });

      return moduleResponseDtos;
   }

}
