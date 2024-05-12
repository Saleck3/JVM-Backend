package com.jvm.lecti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jvm.lecti.dto.request.LoginRequest;
import com.jvm.lecti.service.AuthService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

   @Autowired
   private AuthService authService;

   @ResponseBody
   @RequestMapping(value = "/login", method = RequestMethod.POST)
   public ResponseEntity login(@RequestBody LoginRequest loginRequest) {
      return authService.authenticate(loginRequest);
   }

}