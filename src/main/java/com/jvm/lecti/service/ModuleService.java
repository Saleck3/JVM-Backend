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
public class ModuleService {

   @Autowired
   private ModuleRepository moduleRepository;

   public ModuleResponse getModulesByModuleId(Integer moduleId) {
      List<ModuleEntity> entities = moduleRepository.findAllById(moduleId);
      List<ModuleDto> moduleDtos = mapModuleDto(entities);
      return ModuleResponse.builder().modules(moduleDtos).build();
   }

   private List<ModuleDto> mapModuleDto(List<ModuleEntity> entities) {
      if (entities.isEmpty()) {
         return null;
      }
      List<ModuleDto> moduleList = new ArrayList<>();
      for (ModuleEntity entity : entities) {
         moduleList.add(new ModuleDto(entity.getId(), entity.getDescription()));
      }
      return moduleList;
   }

}
