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

import com.jvm.lecti.presentation.dto.request.LoginRequest;
import com.jvm.lecti.presentation.dto.request.SignUpRequest;
import com.jvm.lecti.presentation.dto.response.ErrorResponse;
import com.jvm.lecti.presentation.dto.response.PlayerDataResponse;
import com.jvm.lecti.domain.entity.Player;
import com.jvm.lecti.domain.entity.SecurityUser;
import com.jvm.lecti.domain.entity.User;
import com.jvm.lecti.infraestructure.repository.UserRepository;

@Service
public class AuthService {

   private static final String SHA_256 = "SHA-256";

   @Autowired
   private AuthenticationManager authenticationManager;

   @Autowired
   private CustomUserDetailsService customUserDetailsService;

   @Autowired
   private UserRepository userRepository;

   public SecurityUser authenticate(LoginRequest loginRequest) {
         authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
         return (SecurityUser) customUserDetailsService.loadUserByUsername(loginRequest.getEmail());
   }

   public ResponseEntity signUpUser(SignUpRequest signUpRequest) {
      List<User> userList = userRepository.findAllByEmail(signUpRequest.getEmail());
      if (userList.isEmpty()) {
         userRepository.save(createNewUser(signUpRequest));
         return ResponseEntity.status(HttpStatus.OK).build();
      } else {
         ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, "Email already in use");
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
      }
   }

   private User createNewUser(SignUpRequest signUpRequest) {
      User user = new User();
      user.setEmail(signUpRequest.getEmail());
      user.setPassword(new MessageDigestPasswordEncoder(SHA_256).encode(signUpRequest.getPassword()));
      user.setFirstName(signUpRequest.getFirstName());
      user.setLastName(signUpRequest.getLastName());
      return user;
   }

   public List<PlayerDataResponse> mapPlayerEntity(List<Player> playerList) {
      List<PlayerDataResponse> playersDataResponse = new ArrayList<>();
      if (!playerList.isEmpty()) {
         for (Player player : playerList) {
            playersDataResponse.add(PlayerDataResponse
                  .builder()
                  .id(player.getId())
                  .playerName(player.getPlayerName())
                  .birthDate(player.getBirthDate())
                  .recomendedModule(player.getRecommendedModule())
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
