package com.jvm.lecti.presentation.controller;

import java.util.List;

import com.jvm.lecti.domain.service.PlayerService;
import com.jvm.lecti.domain.service.UserService;
import com.jvm.lecti.presentation.dto.request.PlayerRequest;
import com.jvm.lecti.presentation.dto.response.ErrorResponse;
import com.jvm.lecti.presentation.dto.response.PlayerResponse;
import com.jvm.lecti.domain.entity.Player;
import com.jvm.lecti.domain.entity.User;
import com.jvm.lecti.presentation.mappers.PlayerMapper;
import com.jvm.lecti.presentation.util.TokenUtil;

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
      User user;
      try {
         Claims claims = tokenUtil.resolveClaims(request);
         String email = claims.getSubject();
         user = userService.getUserByEmail(email);
      } catch (Exception e) {
         ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED, e.getMessage());
         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
      }
      List<Player> players = playerService.getUserPlayers(user.getId());
      if (players.isEmpty()) {
         ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No players assigned to the user");
      }
      return ResponseEntity.ok(PlayerResponse.builder().players(PlayerMapper.INSTANCE.playerListToPlayerListDto(players)).build());
   }

   @PostMapping(value = "/addPlayer")
   public ResponseEntity addPlayer(HttpServletRequest request, @RequestBody PlayerRequest playerRequest) {
      User user;
      try {
         Claims claims = tokenUtil.resolveClaims(request);
         String email = claims.getSubject();
         user = userService.getUserByEmail(email);
      } catch (Exception e) {
         ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED, e.getMessage());
         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
      }
      playerService.addPlayer(playerRequest.getPlayerName(), playerRequest.getBirthDate(), playerRequest.getAlias(), user);
      return ResponseEntity.ok("Player added successfully");
   }

}
