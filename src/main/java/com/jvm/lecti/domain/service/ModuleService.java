package com.jvm.lecti.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jvm.lecti.domain.dao.ModuleDAO;
import com.jvm.lecti.presentation.dto.response.ModuleDto;
import com.jvm.lecti.domain.entity.Module;

import lombok.AllArgsConstructor;

@Service("ModuleService")
@AllArgsConstructor
public class ModuleService {

   private ModuleDAO moduleDAO;

   public Optional<Module> getModuleById(Integer moduleId) {
      return moduleDAO.findById(moduleId);
   }

   public ModuleDto mapModuleDto(Module entity, int progress) {
      return new ModuleDto(entity.getId(), entity.getDescription(), progress);
   }

   public List<Module> findAllModules() {
      return moduleDAO.findAll();
   }

}
