package com.springBoot.MyrPg.exception;

public class DuplicateUserException extends RuntimeException{

    public DuplicateUserException(String message)
    {
        super(message);
    }
}
