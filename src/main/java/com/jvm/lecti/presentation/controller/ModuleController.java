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
   public ResponseEntity getAllModules(HttpServletRequest request, @RequestParam(value = "playerId") Integer playerId) {
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

//   @GetMapping("/{idModule}")
//   public ResponseEntity getApplesFromModule(HttpServletRequest request, @RequestParam(value = "playerId") Integer playerId,
//         @PathVariable Integer idModule) {
//      if (playerId == null) {
//         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(HttpStatus.BAD_REQUEST, "Missing required parameter: playerId"));
//      }
//      ResponseEntity<ErrorResponse> errorResponse = errorResponseUtil.checkPermissionForUser(request, playerId);
//      if (errorResponse != null) {
//         return errorResponse;
//      }
//      Optional<Module> moduleOptional = moduleService.getModuleById(idModule);
//      if (moduleOptional.isEmpty()) {
//         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(HttpStatus.NOT_FOUND, "Module not found"));
//      }
//      Module module = moduleOptional.get();
//      List<AppleResultValue> appleResultValues = appleService.getAppleResultWithPlayerCrowns(module.getId(), playerId);
//      List<AppleDto> appleDtoList = AppleMapper.INSTANCE.appleResultListToAppleDtoList(appleResultValues);
//
//      return ResponseEntity.ok(
//            ModuleAppleResponse.builder().id(module.getId()).description(module.getDescription()).appleList(appleDtoList).build());
//   }

}


