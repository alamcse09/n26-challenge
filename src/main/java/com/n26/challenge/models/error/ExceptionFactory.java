package com.n26.challenge.models.error;

import com.n26.challenge.models.Transaction;

public interface ExceptionFactory {

    TransactionValidationException createTransactionValidationException(Transaction transaction);
}
