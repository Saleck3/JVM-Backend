package com.jvm.lecti.domain.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.jvm.lecti.domain.dao.ModuleDAO;
import com.jvm.lecti.domain.entity.Module;

import lombok.AllArgsConstructor;

@Service("ModuleService")
@AllArgsConstructor
public class ModuleService {

   private ModuleDAO moduleDAO;

   public List<Module> findAllModules() {
      return moduleDAO.findAll();
   }

}
