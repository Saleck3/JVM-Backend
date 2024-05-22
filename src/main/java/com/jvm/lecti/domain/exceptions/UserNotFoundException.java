package com.jvm.lecti.domain.exceptions;

public class UserNotFoundException extends RuntimeException{
   public UserNotFoundException(){
      super("The user could not be found.");
   }
}
