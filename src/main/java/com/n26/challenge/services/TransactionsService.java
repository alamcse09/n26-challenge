package com.n26.challenge.services;

import com.n26.challenge.models.Transaction;

public interface TransactionsService {
    void registerTransaction(Transaction transaction);
}
