package com.jvm.lecti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jvm.lecti.dto.request.LoginRequest;
import com.jvm.lecti.dto.request.SignUpRequest;
import com.jvm.lecti.dto.response.SignUpResponse;
import com.jvm.lecti.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

   @Autowired
   private AuthService authService;

   @PostMapping(value = "/login")
   public ResponseEntity login(@RequestBody LoginRequest loginRequest) {
      return authService.authenticate(loginRequest);
   }

   @PostMapping(value = "/signUp")
   public SignUpResponse signUp(@RequestBody SignUpRequest signUpRequest) {
      return authService.signUpUser(signUpRequest);
   }

}