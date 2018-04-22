package com.n26.challenge.services;

import com.n26.challenge.models.Statistics;
import com.n26.challenge.models.Transaction;

public interface StatisticsCalculatorService {
    void add(Transaction transaction);

    void purgeOldTransactions();

    Statistics getCurrentStatistics();
}
