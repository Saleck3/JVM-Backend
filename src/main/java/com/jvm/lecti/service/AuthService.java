package com.jvm.lecti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.jvm.lecti.dto.request.LoginRequest;
import com.jvm.lecti.dto.response.ErrorResponse;
import com.jvm.lecti.dto.response.LoginResponse;
import com.jvm.lecti.entity.SecurityUser;
import com.jvm.lecti.entity.User;
import com.jvm.lecti.util.TokenUtil;

@Service
public class AuthService {

   @Autowired
   private TokenUtil tokenUtil;

   @Autowired
   private AuthenticationManager authenticationManager;

   @Autowired
   private CustomUserDetailsService customUserDetailsService;

   public ResponseEntity authenticate(LoginRequest loginRequest) {
      try {
         Authentication authentication = authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
         SecurityUser userDetails = (SecurityUser) customUserDetailsService.loadUserByUsername(loginRequest.getEmail());
         User user = userDetails.getUser();
         String token = tokenUtil.createToken(user);
         return ResponseEntity.ok(new LoginResponse(authentication.getName(), token));
      } catch (BadCredentialsException e) {
         ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, "Invalid username or password");
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
      } catch (Exception e) {
         ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
      }
   }

}
