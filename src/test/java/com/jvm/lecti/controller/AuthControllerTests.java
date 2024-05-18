package com.jvm.lecti.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.jvm.lecti.dto.request.LoginRequest;
import com.jvm.lecti.dto.response.LoginResponse;
import com.jvm.lecti.dto.response.PlayerDataResponse;
import com.jvm.lecti.entity.Player;
import com.jvm.lecti.entity.SecurityUser;
import com.jvm.lecti.entity.User;
import com.jvm.lecti.repository.PlayerRepository;
import com.jvm.lecti.service.AuthService;
import com.jvm.lecti.util.TokenUtil;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;

@SpringBootTest
public class AuthControllerTests {

   private static final List<PlayerDataResponse> PLAYER_DATA_RESPONSE_LIST = null;

   public static final String TEST_MAIL = "cannotAccesPlayerIfLoggedUserIsNotOwner@cannotAccesPlayerIfLoggedUserIsNotOwner.com";

   private static final String TEST_PASSWORD = "";

   private static final String JWT_TEST_TOKEN = "eyJhbGciOiJIUzI1NiJ9"
         +
         ".eyJzdWIiOiJzYWxlY2tAbGVjdGkuY29tIiwiZmlyc3ROYW1lIjoie2xOYlhYWUM3amhudGoxOSs3WXBEaUw1alVic3RpcU1CdGhVeHEwSURQUDQ9fTEwZmU2YTdkMGE1M2U2MTljODhjZTkzZmNjYzA0MmYxOWVlM2ZmZDkxMTc3YmE3ODAxNTBmODdiYmIzNmI2YTMiLCJleHAiOjE3MTU0ODM4MjJ9.QfUoXWgOwudntyAaBXw7vpXBNeW8Rh-bOX3icoUOPKY";

   private AuthController authController;

   private AuthService authService;

   private TokenUtil tokenUtil;

   private PlayerRepository playerRepository;

   @Before
   public void init() {
      authService = mock(AuthService.class);
      tokenUtil = mock(TokenUtil.class);
      playerRepository = mock(PlayerRepository.class);
      authController = new AuthController(authService, tokenUtil, playerRepository);
   }

   @Test
   public void whenLoggingInReturnLoginResposeWithJWTAndPlayers() {
      //given
      authServiceAuthenticateIsMocked();
      playerRepositoryIsMocked();
      LoginRequest loginRequest = new LoginRequest(TEST_MAIL, TEST_PASSWORD);
      //when
      ResponseEntity response = whenLogginIn(loginRequest);
      //then
      responseIsLoginResponse(response);
      responseHasJWT(response);
      responseHasPlayer(response);
   }

   @Test
   public void cantSingupWithUsedMail() {
      //TODO
   }

   @Test
   public void canSingupWithAvaiableMail() {
      //TODO
   }

   private void authServiceAuthenticateIsMocked() {
      User user = new User();
      var securityUser = new SecurityUser(user);
      when(this.authService.authenticate(any())).thenReturn(securityUser);
   }

   private void playerRepositoryIsMocked() {
      List<Player> playersList = new ArrayList<>();
      playersList.add(new Player());
      when(this.playerRepository.findByUserId(any())).thenReturn(playersList);
   }

   private ResponseEntity whenLogginIn(LoginRequest loginRequest) {
      return this.authController.login(loginRequest);
   }

   private void responseIsLoginResponse(ResponseEntity response) {

   }

   private void responseHasJWT(ResponseEntity response) {

   }

   private void responseHasPlayer(ResponseEntity response) {
   }

}
