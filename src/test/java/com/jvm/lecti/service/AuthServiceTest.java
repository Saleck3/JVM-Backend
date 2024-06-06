package com.jvm.lecti.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.jvm.lecti.domain.dao.PlayerDAO;
import com.jvm.lecti.domain.dao.UserDAO;
import com.jvm.lecti.domain.entity.Player;
import com.jvm.lecti.domain.entity.SecurityUser;
import com.jvm.lecti.domain.entity.User;
import com.jvm.lecti.domain.service.AuthService;
import com.jvm.lecti.domain.service.CustomUserDetailsService;

public class AuthServiceTest {

   @Mock
   private AuthenticationManager authenticationManager;

   @Mock
   private CustomUserDetailsService customUserDetailsService;

   @Mock
   private UserDAO userDAO;

   @Mock
   private PlayerDAO playerDAO;

   @InjectMocks
   private AuthService authService;

   @BeforeEach
   public void setUp() {
      MockitoAnnotations.openMocks(this);
   }

   @Test
   public void testAuthenticateSuccess() {
      String email = "test@example.com";
      String password = "password";
      SecurityUser mockUser = mock(SecurityUser.class);
      when(customUserDetailsService.loadUserByUsername(email)).thenReturn(mockUser);

      SecurityUser result = authService.authenticate(email, password);

      assertEquals(mockUser, result);
      verify(authenticationManager).authenticate(new UsernamePasswordAuthenticationToken(email, password));
   }

   @Test
   public void testSignUpSuccess() {
      String email = "test@example.com";
      String password = "password";
      String firstName = "First";
      String lastName = "Last";
      String playerName = "PlayerName";
      int recommendedModule = 1;
      when(userDAO.findAllByEmail(email)).thenReturn(Collections.emptyList());

      User mockUser = new User();
      Player mockPlayer = new Player();
      when(userDAO.save(any(User.class))).thenReturn(mockUser);
      when(playerDAO.save(any(Player.class))).thenReturn(mockPlayer);

      ResponseEntity result = authService.signUp(email, password, firstName, lastName, playerName, recommendedModule);

      assertEquals(HttpStatus.OK, result.getStatusCode());
      verify(userDAO).save(any(User.class));
      verify(playerDAO).save(any(Player.class));
   }

   @Test
   public void testSignUpEmailAlreadyInUse() {
      String email = "test@example.com";
      String password = "password";
      String firstName = "First";
      String lastName = "Last";
      String playerName = "PlayerName";

      int recommendedModule = 1;
      when(userDAO.findAllByEmail(email)).thenReturn(Collections.singletonList(new User()));

      ResponseEntity result = authService.signUp(email, password, firstName, lastName, playerName, recommendedModule);

      assertEquals(HttpStatus.CONFLICT, result.getStatusCode());
      verify(userDAO, never()).save(any(User.class));
      verify(playerDAO, never()).save(any(Player.class));
   }

   @Test
   public void testSignUpInternalServerError() {
      String email = "test@example.com";
      String password = "password";
      String firstName = "First";
      String lastName = "Last";
      String playerName = "PlayerName";
      int recommendedModule = 1;
      when(userDAO.findAllByEmail(email)).thenReturn(Collections.emptyList());
      when(userDAO.save(any(User.class))).thenThrow(new RuntimeException());

      ResponseEntity result = authService.signUp(email, password, firstName, lastName, playerName, recommendedModule);

      assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
      verify(userDAO).save(any(User.class));
      verify(playerDAO, never()).save(any(Player.class));
   }

}

