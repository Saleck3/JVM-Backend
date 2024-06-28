package com.jvm.lecti.presentation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jvm.lecti.domain.service.PlayerService;
import com.jvm.lecti.presentation.dto.request.LoginRequest;
import com.jvm.lecti.presentation.dto.request.SignUpRequest;
import com.jvm.lecti.presentation.dto.response.ErrorResponse;
import com.jvm.lecti.presentation.dto.response.LoginResponse;
import com.jvm.lecti.presentation.dto.response.PlayerSessionResponse;
import com.jvm.lecti.domain.entity.Player;
import com.jvm.lecti.domain.entity.SecurityUser;
import com.jvm.lecti.domain.entity.User;
import com.jvm.lecti.domain.service.AuthService;
import com.jvm.lecti.presentation.mappers.PlayerMapper;
import com.jvm.lecti.presentation.util.TokenUtil;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

   @Autowired
   private AuthService authService;

   @Autowired
   private PlayerService playerService;

   @Autowired
   private TokenUtil tokenUtil;

   @PostMapping(value = "/login")
   public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
      SecurityUser userDetails;
      LoginResponse.LoginResponseBuilder responseBuilder = LoginResponse.builder();

      try {
         userDetails = authService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
      } catch (BadCredentialsException e) {
         responseBuilder.errorMessage("Invalid username or password");
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBuilder.build());
      } catch (Exception e) {
         responseBuilder.errorMessage(e.getMessage());
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBuilder.build());
      }

      User user = userDetails.getUser();
      List<Player> playerList = playerService.getPlayersByUserId(user.getId());
      List<PlayerSessionResponse> playersDataResponse = PlayerMapper.INSTANCE.playerListToPlayerDataResponseListDto(playerList);
      String token = tokenUtil.createToken(user);

      responseBuilder.players(playersDataResponse).token(token);

      return ResponseEntity.ok(responseBuilder.build());
   }

   @PostMapping(value = "/signup")
   public ResponseEntity signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
      return authService.signUp(signUpRequest.getEmail(), signUpRequest.getPassword(), signUpRequest.getFirstName(), signUpRequest.getLastName(),
            signUpRequest.getPlayerName(), signUpRequest.getRecommendedModule());
   }

}