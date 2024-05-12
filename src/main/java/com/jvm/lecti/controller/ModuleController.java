package com.jvm.lecti.controller;

import io.jsonwebtoken.Claims;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jvm.lecti.dto.response.ModuleResponse;
import com.jvm.lecti.service.ModuleService;
import com.jvm.lecti.util.TokenUtil;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/modules")
public class ModuleController {

   @Autowired
   private ModuleService moduleService;

   @Autowired
   private TokenUtil tokenUtil;

   @GetMapping("/{idModule}")
   public ModuleResponse getModulesByModuleId(@PathVariable Integer idModule) {
      return moduleService.getModulesByModuleId(idModule);
   }

   @GetMapping("/ruta")
   public String miMetodo(HttpServletRequest request) {
      Claims claims = tokenUtil.resolveClaims(request);
      return "Contenido del JWT: " + claims.get("sub");
   }

}


