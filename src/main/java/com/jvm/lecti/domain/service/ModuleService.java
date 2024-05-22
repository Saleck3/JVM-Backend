package com.jvm.lecti.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jvm.lecti.presentation.dto.response.ModuleDto;
import com.jvm.lecti.domain.entity.Module;
import com.jvm.lecti.repository.ModuleRepository;

import lombok.AllArgsConstructor;

@Service("ModuleService")
@AllArgsConstructor
public class ModuleService {

   @Autowired
   private ModuleRepository moduleRepository;

   public Optional<Module> getModulesByModuleId(Integer moduleId) {
      return moduleRepository.findById(moduleId);
   }

   public ModuleDto mapModuleDto(Module entity,int progress) {
      return new ModuleDto(entity.getId(), entity.getDescription(),progress);
   }

   public List<Module> getAll() {
      return moduleRepository.findAll();
   }

}
