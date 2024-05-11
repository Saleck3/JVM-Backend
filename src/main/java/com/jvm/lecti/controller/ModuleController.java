package com.jvm.lecti.controller;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jvm.lecti.dto.response.ModuleResponse;
import com.jvm.lecti.service.ModuleService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping("/api/modules")
@AllArgsConstructor
@NoArgsConstructor

public class ModuleController {

   @Autowired
   private ModuleService moduleService;

   @GetMapping("/{idModule}")
   public ModuleResponse getModulesByModuleId(@PathVariable Integer idModule) {
      return moduleService.getModulesByModuleId(idModule);
   }

   @GetMapping("/ruta")
   public String miMetodo(@RequestHeader("Authorization") String jwtToken) {
      String jwtTokenT = jwtToken.substring(7);
      Claims claims = Jwts.parser().setSigningKey("h21S8vZQ8YWcxgHmDyBZnPJ72q1LUDvT").parseClaimsJws(jwtTokenT).getBody();
      return "Contenido del JWT: " + claims.get("sub");
   }

}


