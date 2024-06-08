package com.jvm.lecti.presentation.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jvm.lecti.domain.service.AppleService;
import com.jvm.lecti.presentation.dto.response.ErrorResponse;
import com.jvm.lecti.presentation.dto.response.ModuleDto;
import com.jvm.lecti.presentation.dto.response.ModuleResponse;
import com.jvm.lecti.domain.entity.Module;
import com.jvm.lecti.domain.service.ModuleService;
import com.jvm.lecti.presentation.mappers.ModuleMapper;
import com.jvm.lecti.presentation.util.ErrorResponseUtil;

import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;

@RestController
@RequestMapping("/api/modules")
public class ModuleController {

   @Autowired
   private ModuleService moduleService;

   @Autowired
   private AppleService appleService;

   @Autowired
   private ErrorResponseUtil errorResponseUtil;

   @GetMapping
   public ResponseEntity getAllModules(HttpServletRequest request, @NonNull @RequestParam(value = "playerId") Integer playerId) {
      ResponseEntity<ErrorResponse> errorResponse = errorResponseUtil.checkPermissionForUser(request, playerId);
      if (errorResponse != null) {
         return errorResponse;
      }
      List<ModuleDto> moduleDtoList = new ArrayList<>();
      List<Module> modules = moduleService.findAllModules();
      for (Module module : modules) {
         Integer progress = appleService.getModuleProgressValue(module.getId(), playerId);
         moduleDtoList.add(ModuleMapper.INSTANCE.moduleEntityToModuleDto(module, progress));
      }
      return ResponseEntity.ok(ModuleResponse.builder().modules(moduleDtoList).build());
   }

}


