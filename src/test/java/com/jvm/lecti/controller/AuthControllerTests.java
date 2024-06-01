package com.jvm.lecti.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.jvm.lecti.domain.service.PlayerService;
import com.jvm.lecti.presentation.dto.request.LoginRequest;
import com.jvm.lecti.domain.entity.Player;
import com.jvm.lecti.domain.entity.SecurityUser;
import com.jvm.lecti.domain.entity.User;
import com.jvm.lecti.presentation.controller.AuthController;
import com.jvm.lecti.domain.service.AuthService;
import com.jvm.lecti.presentation.util.TokenUtil;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class AuthControllerTests {

   public static final String TEST_MAIL = "cannotAccesPlayerIfLoggedUserIsNotOwner@cannotAccesPlayerIfLoggedUserIsNotOwner.com";

   private static final String TEST_PASSWORD = "";

   private AuthController authController;

   private AuthService authService;

   private TokenUtil tokenUtil;

   private PlayerService playerService;

   @Before
   public void init() {
      authService = mock(AuthService.class);
      tokenUtil = mock(TokenUtil.class);
      playerService = mock(PlayerService.class);
      authController = new AuthController(authService, playerService, tokenUtil);
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
      user.setId(Long.valueOf(1));
      var securityUser = new SecurityUser(user);
      when(this.authService.authenticate(any(), any())).thenReturn(securityUser);
   }

   private void playerRepositoryIsMocked() {
      List<Player> playersList = new ArrayList<>();
      playersList.add(new Player());
      when(this.playerService.getPlayersByUserId(anyLong())).thenReturn(playersList);
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
