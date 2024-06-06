package com.jvm.lecti.domain.exceptions;

public class InvalidErrorQuantityException extends Exception {

   public InvalidErrorQuantityException() {
      super("The error quantity is invalid");
   }

}
