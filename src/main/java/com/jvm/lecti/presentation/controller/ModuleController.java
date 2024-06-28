package com.jvm.lecti.presentation.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jvm.lecti.domain.annotation.CheckPermission;
import com.jvm.lecti.domain.service.AppleService;
import com.jvm.lecti.domain.service.ScoringService;
import com.jvm.lecti.presentation.dto.request.ModuleScoreRequest;
import com.jvm.lecti.presentation.dto.response.ModuleDto;
import com.jvm.lecti.presentation.dto.response.ModuleRecommendedResponse;
import com.jvm.lecti.presentation.dto.response.ModuleResponse;
import com.jvm.lecti.domain.entity.Module;
import com.jvm.lecti.domain.service.ModuleService;
import com.jvm.lecti.presentation.mappers.ModuleMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.NonNull;

@RestController
@RequestMapping("/api/modules")
public class ModuleController {

   @Autowired
   private ModuleService moduleService;

   @Autowired
   private AppleService appleService;

   @Autowired
   private ScoringService scoringService;

   @CheckPermission
   @GetMapping
   public ResponseEntity<ModuleResponse> getAllModules(HttpServletRequest request, @NonNull @RequestParam(value = "playerId") Integer playerId) {
      List<ModuleDto> moduleDtoList = new ArrayList<>();
      List<Module> modules = moduleService.findAllModules();
      for (Module module : modules) {
         Integer progress = appleService.getModuleProgressValue(module.getId(), playerId);
         moduleDtoList.add(ModuleMapper.INSTANCE.moduleEntityToModuleDto(module, progress));
      }
      return ResponseEntity.ok(ModuleResponse.builder().modules(moduleDtoList).build());
   }

   @PostMapping("/recommendedModule")
   public ResponseEntity<ModuleRecommendedResponse> getRecommendedModule(@Valid @RequestBody ModuleScoreRequest request) {
      return ResponseEntity.ok(
            ModuleRecommendedResponse.builder().recommendedModule(scoringService.obtainRecommendedModule(request.getExercises())).build());
   }

}


