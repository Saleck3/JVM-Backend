package com.jvm.lecti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jvm.lecti.dto.ModuleResponseDto;
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

   @GetMapping("{id}")
   public List<ModuleResponseDto> getModulesByUserId(@PathVariable Integer id) {
      var modules = moduleService.getModulesByUserId(1);
      return modules;
   }

}


