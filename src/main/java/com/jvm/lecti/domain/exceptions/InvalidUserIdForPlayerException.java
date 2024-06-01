package com.jvm.lecti.domain.exceptions;

public class InvalidUserIdForPlayerException extends Exception {

   public InvalidUserIdForPlayerException() {
      super("The player do not belong to this user");
   }

}
