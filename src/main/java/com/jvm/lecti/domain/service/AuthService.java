package com.jvm.lecti.domain.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jvm.lecti.domain.dao.PlayerDAO;
import com.jvm.lecti.domain.dao.UserDAO;
import com.jvm.lecti.domain.entity.Player;
import com.jvm.lecti.domain.entity.SecurityUser;
import com.jvm.lecti.domain.entity.User;
import com.jvm.lecti.presentation.dto.response.ErrorResponse;

@Service
public class AuthService {

   private static final String SHA_256 = "SHA-256";

   private static final String GENERIC_ERROR_MESSAGE = "Ocurrio un error inesperado. Intentalo de nuevo más tarde.";

   @Autowired
   private AuthenticationManager authenticationManager;

   @Autowired
   private CustomUserDetailsService customUserDetailsService;

   @Autowired
   private UserDAO userDAO;

   @Autowired
   private PlayerDAO playerDAO;

   public SecurityUser authenticate(String email, String password) {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
      return (SecurityUser) customUserDetailsService.loadUserByUsername(email);
   }

   public ResponseEntity signUp(String email, String password, String firstName, String lastName, String playerName, LocalDate birthDate,
         String alias, int recommendedModule) {
      List<User> userList = userDAO.findAllByEmail(email);
      if (userList.isEmpty()) {
         try {
            User newUser = userDAO.save(createNewUser(email, password, firstName, lastName));
            playerDAO.save(createNewPlayer(playerName, birthDate, alias, newUser, recommendedModule));
            return ResponseEntity.status(HttpStatus.OK).build();
         } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, GENERIC_ERROR_MESSAGE);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
         }
      } else {
         ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT, "Email already in use");
         return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
      }
   }

   private User createNewUser(String email, String password, String firstName, String lastName) {
      User user = new User();
      user.setEmail(email);
      user.setPassword(new MessageDigestPasswordEncoder(SHA_256).encode(password));
      user.setFirstName(firstName);
      user.setLastName(lastName);
      return user;
   }

   private Player createNewPlayer(String playerName, LocalDate birthDate, String alias, User user, int recommendedModule) {
      Player player = new Player();
      player.setPlayerName(playerName);
      player.setBirthDate(birthDate);
      player.setAlias(alias);
      player.setUser(user);
      player.setSpentCrowns(0);
      player.setTotalCrowns(0);
      player.setRecommendedModule(recommendedModule);
      return player;
   }

}
