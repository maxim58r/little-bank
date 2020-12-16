package com.max.littlebank.exeption_handing;

/**
 * @author Serov Maxim
 */
public class TransferException extends RuntimeException {
    public TransferException(String message) {
        super(message);
    }
}
