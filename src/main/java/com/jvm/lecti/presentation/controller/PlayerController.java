package com.jvm.lecti.presentation.controller;

import java.util.ArrayList;
import java.util.List;

import com.jvm.lecti.presentation.dto.request.PlayerRequest;
import com.jvm.lecti.presentation.dto.response.ErrorResponse;
import com.jvm.lecti.presentation.dto.response.PlayerDto;
import com.jvm.lecti.presentation.dto.response.PlayerResponse;
import com.jvm.lecti.domain.entity.Player;
import com.jvm.lecti.domain.entity.User;
import com.jvm.lecti.domain.exceptions.UserNotFoundException;
import com.jvm.lecti.domain.service.PlayerService;
import com.jvm.lecti.domain.service.UserService;
import com.jvm.lecti.presentation.TokenUtil;

import io.jsonwebtoken.Claims;

import jakarta.servlet.http.HttpServletRequest;
import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/player")

@NoArgsConstructor

public class PlayerController {

   @Autowired
   private PlayerService playerService;

   @Autowired
   private TokenUtil tokenUtil;

   @Autowired
   private UserService userService;

   public PlayerController(PlayerService playerService) {
      this.playerService = playerService;
   }

   @GetMapping("/getPlayers")
   public ResponseEntity getPlayers(HttpServletRequest request) {

      Claims claims = null;
      try {
         claims = tokenUtil.resolveClaims(request);
      } catch (Exception e) {
         ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED, e.getMessage());
         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
      }
      String email = claims.get("sub").toString();
      User user;
      try {
         user = userService.getUserByEmail(email);
      } catch (UserNotFoundException e) {
         ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED, e.getMessage());
         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
      }

      List<Player> players = playerService.getUserPlayers(user.getId());
      return ResponseEntity.ok(PlayerResponse.builder().players(mapModuleDto(players)).build());
   }

   @PostMapping(value = "/addPlayer")
   public ResponseEntity addPlayer(HttpServletRequest request, @RequestBody PlayerRequest playerRequest) {
      Claims claims = null;
      try {
         claims = tokenUtil.resolveClaims(request);
      } catch (Exception e) {
         ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED, e.getMessage());
         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
      }
      String email = claims.get("sub").toString();
      User user;
      try {
         user = userService.getUserByEmail(email);
      } catch (UserNotFoundException e) {
         ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED, e.getMessage());
         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
      }
      Player player = playerService.addPlayer(playerRequest, user);
      List<Player> players = new ArrayList<>();
      players.add(player);
      return ResponseEntity.ok(PlayerResponse.builder().players(mapModuleDto(players)).build());
   }

   private List<PlayerDto> mapModuleDto(List<Player> players) {
      if (players.isEmpty()) {
         return null;
      }
      List<PlayerDto> playerList = new ArrayList<>();
      for (Player entity : players) {
         playerList.add(new PlayerDto(entity.getId(), entity.getPlayerName(), entity.getBirthDate(), entity.getTotalCrowns(), entity.getSpentCrowns(),
               entity.getAlias()));
      }
      return playerList;
   }

}
