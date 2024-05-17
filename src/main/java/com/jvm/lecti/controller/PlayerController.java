package com.jvm.lecti.controller;

import com.jvm.lecti.dto.request.PlayerRequest;
import com.jvm.lecti.dto.response.ExerciseResponse;
import com.jvm.lecti.dto.response.PlayerDto;
import com.jvm.lecti.dto.response.PlayerResponse;
import com.jvm.lecti.entity.Player;
import com.jvm.lecti.service.ExerciseService;
import com.jvm.lecti.service.PlayerService;
import com.jvm.lecti.util.TokenUtil;
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

   public PlayerController(PlayerService playerService){
      this.playerService = playerService;
   }

//   @GetMapping("/getExercise/{id}")
//   public AppleResponse getApple(@PathVariable Integer appleId) {
//      return exerciseService.get(appleId);
//   }


   @GetMapping("/getUserPlayers")
   public PlayerResponse getPlayersFromUserId(@RequestParam(value = "userId") long userId) {
      PlayerResponse playerResponse = playerService.getUserPlayers(userId);
      return playerResponse;
   }

   @PostMapping(value = "/addPlayer")
   public PlayerDto addPlayer(HttpServletRequest request, @RequestBody PlayerRequest playerRequest){
      //TODO SETEAR DENTRO DEL JWT
      Claims claims = tokenUtil.resolveClaims(request);
      String email = claims.get("sub").toString();
      return playerService.addPlayer(playerRequest, email);
   }

}
