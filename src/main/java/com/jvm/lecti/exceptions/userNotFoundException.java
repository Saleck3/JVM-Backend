package com.jvm.lecti.exceptions;

public class userNotFoundException extends Exception{
   public userNotFoundException(){
      super("The user could not be found.");
   }
}
