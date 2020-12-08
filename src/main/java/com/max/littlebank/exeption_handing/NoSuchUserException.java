package com.max.littlebank.exeption_handing;

/**
 * @author Serov Maxim
 */
public class NoSuchUserException extends RuntimeException{
    public NoSuchUserException(String message) {
        super(message);
    }
}
