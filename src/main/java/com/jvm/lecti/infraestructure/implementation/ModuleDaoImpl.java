package com.jvm.lecti.infraestructure.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jvm.lecti.domain.dao.ModuleDAO;
import com.jvm.lecti.domain.entity.Module;
import com.jvm.lecti.infraestructure.repository.ModuleRepository;

@Component
public class ModuleDaoImpl implements ModuleDAO {

   @Autowired
   private ModuleRepository moduleRepository;

   public ModuleDaoImpl(ModuleRepository moduleRepository) {
      this.moduleRepository = moduleRepository;
   }

   @Override
   public Optional<Module> findById(Integer id) {
      return moduleRepository.findById(id);
   }

   @Override
   public List<Module> findAll() {
      return moduleRepository.findAll();
   }

}
