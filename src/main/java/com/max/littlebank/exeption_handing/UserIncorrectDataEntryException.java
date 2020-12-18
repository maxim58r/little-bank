package com.max.littlebank.exeption_handing;

/**
 * @author Serov Maxim
 */
public class UserIncorrectDataEntryException extends RuntimeException{
    public UserIncorrectDataEntryException(String message) {
        super(message);
    }
}
