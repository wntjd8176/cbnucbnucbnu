package com.cbnuDiary.demo.Exception;

public class userIDAlreadyExistsException extends RuntimeException{
    public userIDAlreadyExistsException(String message){
        super(message);
    }
}
