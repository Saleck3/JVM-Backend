package com.jvm.lecti.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.jvm.lecti.dto.request.LoginRequest;
import com.jvm.lecti.dto.response.LoginResponse;
import com.jvm.lecti.service.AuthService;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AuthControllerTests {

   public static final String TEST_MAIL = "test@test.com";

   public static final String TEST_PASSWORD = "";

   public static final String JWT_TEST_TOKEN = "eyJhbGciOiJIUzI1NiJ9"
         +
         ".eyJzdWIiOiJzYWxlY2tAbGVjdGkuY29tIiwiZmlyc3ROYW1lIjoie2xOYlhYWUM3amhudGoxOSs3WXBEaUw1alVic3RpcU1CdGhVeHEwSURQUDQ9fTEwZmU2YTdkMGE1M2U2MTljODhjZTkzZmNjYzA0MmYxOWVlM2ZmZDkxMTc3YmE3ODAxNTBmODdiYmIzNmI2YTMiLCJleHAiOjE3MTU0ODM4MjJ9.QfUoXWgOwudntyAaBXw7vpXBNeW8Rh-bOX3icoUOPKY";

   private AuthController authController;

   private AuthService authService;

   @Before
   public void init() {
      authService = mock(AuthService.class);
      authController = new AuthController(authService);
   }

   @Test
   public void whenLoggingInReturnLoginResposeWithJWTAndEmail() {
      //given
      authServiceAuthenticateIsMocked();
      LoginRequest loginRequest = new LoginRequest(TEST_MAIL, TEST_PASSWORD);
      //when
      ResponseEntity response = whenLogginIn(loginRequest);
      //then
      responseIsLoginResponse(response);
/*      responseHasJWT(responseLogin);
      responseHasEmail(responseLogin);*/
   }

   private void authServiceAuthenticateIsMocked() {
      var loginResponse=new LoginResponse(TEST_MAIL, JWT_TEST_TOKEN);
      when(this.authService.authenticate(any())).thenReturn(loginResponse);
   }

   private ResponseEntity whenLogginIn(LoginRequest loginRequest) {
      return this.authController.login(loginRequest);
   }

   private void responseIsLoginResponse(ResponseEntity response) {
      assert (response.getBody() instanceof LoginResponse);
   }

   private void responseHasJWT(ResponseEntity response) {

   }

   private void responseHasEmail(ResponseEntity response) {
   }

}
