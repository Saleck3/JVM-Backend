package com.jvm.lecti.exceptions;

public class InvalidUserIdForPlayerException extends Exception{

    public InvalidUserIdForPlayerException(){
        super("The player do not belong to this user");
    }
}
