package com.n26.challenge.models.error;

import com.n26.challenge.models.Transaction;
import org.springframework.stereotype.Component;

@Component
public class ExceptionFactoryImpl implements ExceptionFactory {

    @Override
    public TransactionValidationException createTransactionValidationException(Transaction transaction) {
        String message = String.format("Transaction is too old: %s", transaction.toString());
        return new TransactionValidationException(message);
    }
}
