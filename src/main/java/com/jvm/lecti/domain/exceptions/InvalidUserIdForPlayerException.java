package com.jvm.lecti.domain.exceptions;

public class InvalidUserIdForPlayerException extends RuntimeException{

    public InvalidUserIdForPlayerException(){
        super("The player do not belong to this user");
    }
}
