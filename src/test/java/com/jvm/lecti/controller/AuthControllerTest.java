package com.jvm.lecti.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.jvm.lecti.domain.entity.SecurityUser;
import com.jvm.lecti.domain.entity.User;
import com.jvm.lecti.domain.service.AuthService;
import com.jvm.lecti.domain.service.PlayerService;
import com.jvm.lecti.presentation.controller.AuthController;
import com.jvm.lecti.presentation.dto.request.LoginRequest;
import com.jvm.lecti.presentation.util.TokenUtil;

@SpringBootTest
public class AuthControllerTest {

   @Mock
   private AuthService authService;

   @Mock
   private PlayerService playerService;

   @Mock
   private TokenUtil tokenUtil;

   @InjectMocks
   private AuthController authController;

   @BeforeEach
   public void setUp() {
      MockitoAnnotations.openMocks(this);
   }

   @Test
   public void testLoginValidCredentials() {
      LoginRequest loginRequest = new LoginRequest("test@example.com", "password");
      User user = new User();
      user.setId(Long.valueOf(1));
      user.setEmail("test@example.com");
      user.setPassword("password");
      SecurityUser mockUserDetails = new SecurityUser(user);
      when(authService.authenticate(loginRequest.getEmail(), loginRequest.getPassword())).thenReturn(mockUserDetails);

      ResponseEntity responseEntity = authController.login(loginRequest);

      assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
   }

   @Test
   public void testLoginInvalidCredentials() {
      LoginRequest loginRequest = new LoginRequest("test@example.com", "wrong_password");
      when(authService.authenticate(loginRequest.getEmail(), loginRequest.getPassword())).thenThrow(
            new BadCredentialsException("Invalid username or password"));

      ResponseEntity responseEntity = authController.login(loginRequest);

      assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
   }

   @Test
   public void testLoginExceptionThrown() {
      LoginRequest loginRequest = new LoginRequest("test@example.com", "password");
      when(authService.authenticate(loginRequest.getEmail(), loginRequest.getPassword())).thenThrow(new RuntimeException("Some unexpected error"));

      ResponseEntity responseEntity = authController.login(loginRequest);

      assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
   }

}
