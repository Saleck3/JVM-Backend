package com.jvm.lecti.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jvm.lecti.domain.dao.UserDAO;
import com.jvm.lecti.domain.entity.Player;
import com.jvm.lecti.domain.entity.SecurityUser;
import com.jvm.lecti.domain.entity.User;
import com.jvm.lecti.presentation.dto.response.ErrorResponse;
import com.jvm.lecti.presentation.dto.response.PlayerSessionResponse;

@Service
public class AuthService {

   private static final String SHA_256 = "SHA-256";

   @Autowired
   private AuthenticationManager authenticationManager;

   @Autowired
   private CustomUserDetailsService customUserDetailsService;

   @Autowired
   private UserDAO userDAO;

   public SecurityUser authenticate(String email, String password) {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
      return (SecurityUser) customUserDetailsService.loadUserByUsername(email);
   }

   public ResponseEntity signUpUser(String email, String password, String firstName, String lastName) {
      List<User> userList = userDAO.findAllByEmail(email);
      if (userList.isEmpty()) {
         userDAO.save(createNewUser(email, password, firstName, lastName));
         return ResponseEntity.status(HttpStatus.OK).build();
      } else {
         ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, "Email already in use");
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
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

   public List<PlayerSessionResponse> mapPlayerEntity(List<Player> playerList) {
      List<PlayerSessionResponse> playersDataResponse = new ArrayList<>();
      if (!playerList.isEmpty()) {
         for (Player player : playerList) {
            playersDataResponse.add(
                  PlayerSessionResponse
                        .builder()
                        .id(player.getId())
                        .playerName(player.getPlayerName())
                        .birthDate(player.getBirthDate())
                        //Avisar al front
                        .recommendedModule(player.getRecommendedModule())
                        .spentCrowns(player.getSpentCrowns())
                        .totalCrowns(player.getTotalCrowns())
                        .alias(player.getAlias())
                        .build());
         }
         return playersDataResponse;
      }
      return null;
   }

}
