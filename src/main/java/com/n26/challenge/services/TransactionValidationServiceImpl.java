package com.n26.challenge.services;

import com.n26.challenge.models.Transaction;
import com.n26.challenge.models.error.ExceptionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TransactionValidationServiceImpl implements TransactionValidationService {

    private static final long MAX_VALID_AGE_IN_SECONDS = 60;
    private final ExceptionFactory exceptionFactory;

    @Autowired
    public TransactionValidationServiceImpl(ExceptionFactory exceptionFactory) {
        this.exceptionFactory = exceptionFactory;
    }

    @Override
    public void validate(Transaction transaction) {
        if (!isValid(transaction)) {
            throw exceptionFactory.createTransactionValidationException(transaction);
        }
    }

    private boolean isValid(Transaction transaction) {
        Instant instantOfTransaction = Instant.ofEpochMilli(transaction.getTimestamp());
        Instant oldestValidInstant = Instant.now().minusSeconds(MAX_VALID_AGE_IN_SECONDS);

        return !instantOfTransaction.isBefore(oldestValidInstant);
    }
}
