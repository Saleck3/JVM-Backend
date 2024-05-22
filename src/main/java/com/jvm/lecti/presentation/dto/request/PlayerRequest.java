package com.jvm.lecti.presentation.dto.request;

import com.jvm.lecti.domain.entity.Player;

import lombok.Data;

@Data
public class PlayerRequest {

   private String firstName;

   private String birthDate;

   private String alias;

   public Player toModel(){
      //Todo pasar los parametros para que tenga los datos
      return new Player();
   }

}
