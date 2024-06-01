package com.jvm.lecti.domain.service;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.jvm.lecti.domain.dao.ModuleDAO;
import com.jvm.lecti.domain.entity.Apple;
import com.jvm.lecti.domain.entity.Exercise;
import com.jvm.lecti.domain.entity.Module;

import lombok.AllArgsConstructor;

@Service("ModuleService")
@AllArgsConstructor
public class ModuleService {

   private ModuleDAO moduleDAO;

   public List<Module> findAllModules() {
      return moduleDAO.findAll();
   }

   public Integer obtainModuleIdFromExercise(List<Exercise> exerciseList) {
      if (!exerciseList.isEmpty()) {
         Exercise exercise = exerciseList.get(0);
         return exercise.getApple().getModule().getId();
      }
      return null;
   }

}
