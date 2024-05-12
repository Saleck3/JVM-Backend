package com.jvm.lecti.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jvm.lecti.dto.request.LoginRequest;
import com.jvm.lecti.dto.request.SignUpRequest;
import com.jvm.lecti.dto.response.ErrorResponse;
import com.jvm.lecti.dto.response.LoginResponse;
import com.jvm.lecti.dto.response.PlayerDataResponse;
import com.jvm.lecti.entity.Player;
import com.jvm.lecti.entity.SecurityUser;
import com.jvm.lecti.entity.User;
import com.jvm.lecti.repository.PlayerRepository;
import com.jvm.lecti.repository.UserRepository;
import com.jvm.lecti.util.TokenUtil;

@Service
public class AuthService {

   private static final String SHA_256 = "SHA-256";

   @Autowired
   private TokenUtil tokenUtil;

   @Autowired
   private AuthenticationManager authenticationManager;

   @Autowired
   private CustomUserDetailsService customUserDetailsService;

   @Autowired
   private UserRepository userRepository;

   @Autowired
   private PlayerRepository playerRepository;

   public ResponseEntity authenticate(LoginRequest loginRequest) {
      try {
         authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
         SecurityUser userDetails = (SecurityUser) customUserDetailsService.loadUserByUsername(loginRequest.getEmail());
         User user = userDetails.getUser();
         String token = tokenUtil.createToken(user);
         List<Player> playerList = playerRepository.findByUserId(user.getId());
         List<PlayerDataResponse> playersDataResponse = mapPlayerEntity(playerList);
         return ResponseEntity.ok(LoginResponse.builder().players(playersDataResponse).token(token).build());
      } catch (BadCredentialsException e) {
         ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, "Invalid username or password");
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
      } catch (Exception e) {
         ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
      }
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

   private List<PlayerDataResponse> mapPlayerEntity(List<Player> playerList) {
      List<PlayerDataResponse> playersDataResponse = new ArrayList<>();
      if (!playerList.isEmpty()) {
         for (Player player : playerList) {
            playersDataResponse.add(PlayerDataResponse
                  .builder()
                  .id(player.getId())
                  .playerName(player.getPlayerName())
                  .birthDate(player.getBirthDate())
                  .recomendedModule(player.getRecomendedModule())
                  .spentCrowns(player.getSpentCrowns())
                  .totalCrowns(player.getTotalCrowns())
                  .build());
         }
         return playersDataResponse;
      }
      return null;
   }

}
