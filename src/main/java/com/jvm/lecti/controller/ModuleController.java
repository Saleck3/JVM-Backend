package com.jvm.lecti.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jvm.lecti.entity.ModulelEntity;
import com.jvm.lecti.service.ModuleService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping("/modules")
@AllArgsConstructor
@NoArgsConstructor

   public class ModuleController {

      @Autowired
      private ModuleService moduleService;
   @GetMapping("/getByUserId")
   public ArrayList<ModulelEntity> getModulesByUserId() {
      var modules = moduleService.getModulesByUserId(1);
      return modules;
   }
   }


