package com.jvm.lecti.exceptions;

public class UserNotFoundException extends Exception {

   public UserNotFoundException() {
      super("The user could not be found.");
   }

}
