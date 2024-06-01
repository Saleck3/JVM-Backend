package com.jvm.lecti.domain.exceptions;

public class UserNotFoundException extends Exception {

   public UserNotFoundException() {
      super("The user could not be found.");
   }

}
