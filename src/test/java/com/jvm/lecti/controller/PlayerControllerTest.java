package com.jvm.lecti.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import io.jsonwebtoken.Claims;

import com.jvm.lecti.domain.entity.Player;
import com.jvm.lecti.domain.entity.User;
import com.jvm.lecti.domain.service.PlayerService;
import com.jvm.lecti.domain.service.UserService;
import com.jvm.lecti.presentation.controller.PlayerController;
import com.jvm.lecti.presentation.dto.request.PlayerRequest;
import com.jvm.lecti.presentation.dto.response.ErrorResponse;
import com.jvm.lecti.presentation.dto.response.PlayerResponse;
import com.jvm.lecti.presentation.util.TokenUtil;

import jakarta.servlet.http.HttpServletRequest;

public class PlayerControllerTest {

   @Mock
   private PlayerService playerService;

   @Mock
   private TokenUtil tokenUtil;

   @Mock
   private UserService userService;

   @InjectMocks
   private PlayerController playerController;

   @BeforeEach
   public void setUp() {
      MockitoAnnotations.openMocks(this);
      playerController = new PlayerController(playerService, tokenUtil, userService);
   }

   @Test
   public void testGetPlayersUnauthorized() throws Exception {
      HttpServletRequest request = mock(HttpServletRequest.class);
      when(tokenUtil.resolveClaims(request)).thenThrow(new Exception("Unauthorized"));

      ResponseEntity responseEntity = playerController.getPlayers(request);

      assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
      ErrorResponse errorResponse = (ErrorResponse) responseEntity.getBody();
      assertEquals("Unauthorized", errorResponse.getMessage());
   }

   @Test
   public void testGetPlayersSuccess() throws Exception {
      User mockUser = new User();
      mockUser.setId(Long.valueOf(1));
      List<Player> mockPlayers = new ArrayList<>();
      mockPlayers.add(new Player());
      HttpServletRequest request = mock(HttpServletRequest.class);
      Claims mockClaims = mock(Claims.class);
      when(tokenUtil.resolveClaims(request)).thenReturn(mockClaims);
      when(mockClaims.getSubject()).thenReturn("test@example.com");
      when(userService.getUserByEmail("test@example.com")).thenReturn(mockUser);
      when(playerService.getUserPlayers(mockUser.getId())).thenReturn(mockPlayers);

      ResponseEntity responseEntity = playerController.getPlayers(request);

      assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
      PlayerResponse playerResponse = (PlayerResponse) responseEntity.getBody();
      assertEquals(1, playerResponse.getPlayers().size());
   }

   @Test
   public void testAddPlayerUnauthorized() throws Exception {
      PlayerRequest playerRequest = new PlayerRequest();
      HttpServletRequest request = mock(HttpServletRequest.class);
      when(tokenUtil.resolveClaims(request)).thenThrow(new Exception("Unauthorized"));

      ResponseEntity responseEntity = playerController.addPlayer(request, playerRequest);

      assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
      ErrorResponse errorResponse = (ErrorResponse) responseEntity.getBody();
      assertEquals("Unauthorized", errorResponse.getMessage());
   }

   @Test
   public void testAddPlayerSuccess() throws Exception {
      PlayerRequest playerRequest = new PlayerRequest();
      User mockUser = new User();
      mockUser.setId(Long.valueOf(1));
      HttpServletRequest request = mock(HttpServletRequest.class);
      Claims mockClaims = mock(Claims.class);
      when(tokenUtil.resolveClaims(request)).thenReturn(mockClaims);
      when(mockClaims.getSubject()).thenReturn("test@example.com");
      when(userService.getUserByEmail("test@example.com")).thenReturn(mockUser);

      ResponseEntity responseEntity = playerController.addPlayer(request, playerRequest);

      assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
      assertEquals("Player added successfully", responseEntity.getBody());
   }
}