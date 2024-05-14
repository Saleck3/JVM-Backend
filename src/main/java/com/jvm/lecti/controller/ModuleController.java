package com.jvm.lecti.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import io.jsonwebtoken.Claims;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jvm.lecti.dto.response.ErrorResponse;
import com.jvm.lecti.dto.response.ModuleDto;
import com.jvm.lecti.dto.response.ModuleResponse;
import com.jvm.lecti.dto.response.ModulesResponse;
import com.jvm.lecti.entity.Apple;
import com.jvm.lecti.entity.Module;
import com.jvm.lecti.service.AppleService;
import com.jvm.lecti.service.ModuleService;
import com.jvm.lecti.util.TokenUtil;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/modules")
public class ModuleController {

   @Autowired
   private ModuleService moduleService;

   @Autowired
   private AppleService appleService;

   @Autowired
   private TokenUtil tokenUtil;

   @GetMapping("/")
   public ResponseEntity getAllModules(HttpServletRequest request) {
      List<Module> modules = moduleService.getAll();
      List<ModuleDto> moduleList = new ArrayList<>();
      Claims claims = tokenUtil.resolveClaims(request);
      int playerId = (int) claims.get("playerId");
      List<Apple> ApplesInModule;

      for (Module module : modules) {
         int max_score = 0;
         int player_score = 0;
         int progress = 0;

         ApplesInModule = appleService.getApples(module.getId(), playerId);
         for (Apple apple : ApplesInModule) {
            max_score += apple.getMax_score();
            player_score += apple.getScore();
         }
         if(player_score != 0) {
            progress = (player_score * 100) / max_score;
         }

         moduleList.add(moduleService.mapModuleDto(module, progress));
      }
      return ResponseEntity.ok(ModulesResponse.builder().modules(moduleList).build());
   }

   @GetMapping("/{idModule}")
   public ResponseEntity getModulesByModuleId(HttpServletRequest request, @PathVariable Integer idModule) {
      Optional<Module> module = moduleService.getModulesByModuleId(idModule);
      if (module.orElse(null) == null) {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(HttpStatus.NOT_FOUND, "Module not found"));
      }

      Claims claims = tokenUtil.resolveClaims(request);
      List<Apple> moduleApples = appleService.getApples(module.get().getId(), (int) claims.get("playerId"));

      return ResponseEntity.ok(
            ModuleResponse.builder().id(module.get().getId()).description(module.get().getDescription()).appleList(moduleApples).build());
   }

   @GetMapping("/ruta")
   public String miMetodo(HttpServletRequest request) {
      Claims claims = tokenUtil.resolveClaims(request);
      return "Contenido del JWT: " + claims.get("sub");
   }
/*
   public ModuleResponse getAll(HttpServletRequest request){
      return moduleService.findAll();
      //lamar a la DB traerme todos los  modulos
//      foreach
      //apple service que me traiga manzanas del modulo
//      List<apple> =appleService.getApplesFromMolude(1);
      //foreach
         //calculatePercentage star

   }*/

}


