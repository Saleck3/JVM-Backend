package com.jvm.lecti.dto.response;

import com.jvm.lecti.entity.Player;

import java.util.List;

import lombok.Builder;

@Builder
public class PlayerResponse {

   List<PlayerDto> players;

   public PlayerResponse(List<PlayerDto> players){
      this.players = players;
   }

   public List<PlayerDto> getPlayers() {
      return players;
   }

   public void setPlayers(List<PlayerDto> players) {
      this.players = players;
   }
}
