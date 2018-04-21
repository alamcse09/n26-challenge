package com.n26.challenge.models.error;

public class TransactionValidationException extends RuntimeException {

    TransactionValidationException(String message) {
        super(message);
    }
}
