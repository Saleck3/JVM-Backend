package com.jvm.lecti.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jvm.lecti.dto.response.ModuleDto;
import com.jvm.lecti.dto.response.ModuleResponse;
import com.jvm.lecti.entity.ModuleEntity;
import com.jvm.lecti.repository.ModuleRepository;

import lombok.AllArgsConstructor;

@Service("ModuleService")
@AllArgsConstructor
public class ModuleServiceImpl implements ModuleService {

   @Autowired
   private ModuleRepository moduleRepository;

   @Override
   public ModuleResponse getModulesByModuleId(Integer moduleId) {
      var modules = moduleRepository.findAllById(moduleId);
      var modulesDto = getModuleResponseDto(modules);
      return modulesDto;
   }

   private ModuleResponse getModuleResponseDto(List<ModuleEntity> modules) {
      ModuleResponse moduleResponse = new ModuleResponse();
      modules.forEach(moduleEntity -> {
         var moduleResponseDto = new ModuleDto(moduleEntity.getId(),moduleEntity.getDescription());
         moduleResponse.addModule(moduleResponseDto);
      });
      return moduleResponse;
   }

}
