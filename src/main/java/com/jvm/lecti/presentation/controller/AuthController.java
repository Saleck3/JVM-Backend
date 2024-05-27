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

import com.jvm.lecti.presentation.dto.request.LoginRequest;
import com.jvm.lecti.presentation.dto.request.SignUpRequest;
import com.jvm.lecti.presentation.dto.response.ErrorResponse;
import com.jvm.lecti.presentation.dto.response.LoginResponse;
import com.jvm.lecti.presentation.dto.response.PlayerDataResponse;
import com.jvm.lecti.domain.entity.Player;
import com.jvm.lecti.domain.entity.SecurityUser;
import com.jvm.lecti.domain.entity.User;
import com.jvm.lecti.infraestructure.repository.PlayerRepository;
import com.jvm.lecti.domain.service.AuthService;
import com.jvm.lecti.presentation.util.TokenUtil;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

   @Autowired
   private AuthService authService;

   @Autowired
   private TokenUtil tokenUtil;

   @Autowired
   private PlayerRepository playerRepository;

   @PostMapping(value = "/login")
   public ResponseEntity login(@RequestBody LoginRequest loginRequest) {

      SecurityUser userDetails;
      try {
         userDetails = authService.authenticate(loginRequest);
      } catch (BadCredentialsException e) {
         ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, "Invalid username or password");
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
      } catch (Exception e) {
         ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
      }
      User user = userDetails.getUser();
      List<Player> playerList = playerRepository.findByUserId(user.getId());
      List<PlayerDataResponse> playersDataResponse = authService.mapPlayerEntity(playerList);

      String token = tokenUtil.createToken(user);
      return ResponseEntity.ok(LoginResponse.builder().players(playersDataResponse).token(token).build());
   }

   @PostMapping(value = "/signup")
   public ResponseEntity signUp(@RequestBody SignUpRequest signUpRequest) {
      return authService.signUpUser(signUpRequest);
   }

   //TODO ChangePassword
}