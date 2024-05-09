package com.jvm.lecti.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jvm.lecti.entity.ModulelEntity;
import com.jvm.lecti.repository.ModuleRepository;

@Service("ModuleService")
@Transactional
public class ModuleServiceImpl implements ModuleService {

   @Autowired
   private ModuleRepository moduleRepository;


   @Override
   public ArrayList<ModulelEntity> getModulesByUserId(Integer id) {
      return moduleRepository.findAllById(id);
   }
}
