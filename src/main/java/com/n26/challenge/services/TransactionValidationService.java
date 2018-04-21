package com.n26.challenge.services;

import com.n26.challenge.models.Transaction;

public interface TransactionValidationService {

    boolean isValid(Transaction transaction);
}
