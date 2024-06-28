package com.jvm.lecti.presentation.controller;

import java.util.List;

import com.jvm.lecti.domain.service.PlayerService;
import com.jvm.lecti.domain.service.UserService;
import com.jvm.lecti.presentation.dto.request.PlayerRequest;
import com.jvm.lecti.presentation.dto.response.PlayerResponse;
import com.jvm.lecti.domain.entity.Player;
import com.jvm.lecti.domain.entity.User;
import com.jvm.lecti.presentation.mappers.PlayerMapper;
import com.jvm.lecti.presentation.util.TokenUtil;

import io.jsonwebtoken.Claims;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/player")
@NoArgsConstructor
@AllArgsConstructor
public class PlayerController {

   @Autowired
   private PlayerService playerService;

   @Autowired
   private TokenUtil tokenUtil;

   @Autowired
   private UserService userService;

   @GetMapping("/getPlayers")
   public ResponseEntity<PlayerResponse> getPlayers(HttpServletRequest request) throws Exception {
      Claims claims = tokenUtil.resolveClaims(request);
      User user = userService.getUserByEmail(claims.getSubject());
      List<Player> players = playerService.getUserPlayers(user.getId());
      return ResponseEntity.ok(PlayerResponse.builder().players(PlayerMapper.INSTANCE.playerListToPlayerListDto(players)).build());
   }

   @PostMapping(value = "/addPlayer")
   public void addPlayer(HttpServletRequest request, @RequestBody PlayerRequest playerRequest) throws Exception {
      Claims claims = tokenUtil.resolveClaims(request);
      User user = userService.getUserByEmail(claims.getSubject());
      playerService.addPlayer(playerRequest.getPlayerName(), playerRequest.getBirthDate(), user);
   }

}
