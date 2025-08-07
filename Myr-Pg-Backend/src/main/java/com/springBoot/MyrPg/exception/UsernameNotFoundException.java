package com.springBoot.MyrPg.exception;

public class UsernameNotFoundException extends RuntimeException{

    public UsernameNotFoundException(String message)
    {
        super(message);
    }
}
