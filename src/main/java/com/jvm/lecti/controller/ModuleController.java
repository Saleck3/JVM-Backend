package com.jvm.lecti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jvm.lecti.dto.response.ModuleResponse;
import com.jvm.lecti.service.ModuleService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping("api/modules")
@AllArgsConstructor
@NoArgsConstructor

public class ModuleController {

   @Autowired
   private ModuleService moduleService;

   @GetMapping("/{idModule}")
   public ModuleResponse getModulesByModuleId(@PathVariable Integer idModule) {
      var modules = moduleService.getModulesByModuleId(idModule);
      return modules;
   }

}


