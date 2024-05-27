package com.jvm.lecti.presentation.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import io.jsonwebtoken.Claims;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jvm.lecti.domain.service.AppleService;
import com.jvm.lecti.domain.service.PlayerService;
import com.jvm.lecti.presentation.dto.response.ErrorResponse;
import com.jvm.lecti.presentation.dto.response.ModuleDto;
import com.jvm.lecti.presentation.dto.response.ModuleResponse;
import com.jvm.lecti.presentation.dto.response.ModulesResponse;
import com.jvm.lecti.domain.entity.Apple;
import com.jvm.lecti.domain.entity.Module;
import com.jvm.lecti.exceptions.InvalidUserIdForPlayerException;
import com.jvm.lecti.domain.service.ModuleService;
import com.jvm.lecti.presentation.util.ErrorResponseUtil;
import com.jvm.lecti.presentation.util.TokenUtil;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/modules")
public class ModuleController {

   @Autowired
   private ModuleService moduleService;

   @Autowired
   private AppleService appleService;

   @Autowired
   private ErrorResponseUtil errorResponseUtil;

   @GetMapping("/")
   public ResponseEntity getAllModules(HttpServletRequest request, @RequestParam(value = "playerId") Integer playerId) {
      ResponseEntity<ErrorResponse> errorResponse = errorResponseUtil.getErrorResponse(request, playerId);
      if (errorResponse != null) {
         return errorResponse;
      }

      //Modificar
      List<ModuleDto> moduleList = new ArrayList<>();
      List<Module> modules = moduleService.getAll();
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
         if (player_score != 0) {
            progress = (player_score * 100) / max_score;
         }
         moduleList.add(moduleService.mapModuleDto(module, progress));
      }
      return ResponseEntity.ok(ModulesResponse.builder().modules(moduleList).build());
   }

   @GetMapping("/{idModule}")
   public ResponseEntity getModulesByModuleId(HttpServletRequest request, @RequestParam(value = "playerId", required = false) Integer playerId,
         @PathVariable Integer idModule) {
      if (playerId == null) {
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(HttpStatus.BAD_REQUEST, "Missing required parameter: playerId"));
      }
      ResponseEntity<ErrorResponse> errorResponse = errorResponseUtil.getErrorResponse(request, playerId);
      if (errorResponse != null) {
         return errorResponse;
      }

      //Modificar
      Optional<Module> module = moduleService.getModuleById(idModule);
      if (module.orElse(null) == null) {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(HttpStatus.NOT_FOUND, "Module not found"));
      }

      List<Apple> moduleApples = appleService.getApples(module.get().getId(), playerId);

      return ResponseEntity.ok(
            ModuleResponse.builder().id(module.get().getId()).description(module.get().getDescription()).appleList(moduleApples).build());
   }

}


