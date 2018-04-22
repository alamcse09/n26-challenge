package com.n26.challenge.services;

import com.n26.challenge.models.Transaction;
import org.springframework.stereotype.Service;

@Service
public class TransactionsServiceImpl implements TransactionsService {

    private final TransactionValidationService transactionValidationService;
    private final StatisticsCalculatorService statisticsCalculatorService;

    public TransactionsServiceImpl(TransactionValidationService transactionValidationService,
        StatisticsCalculatorService statisticsCalculatorService) {
        this.transactionValidationService = transactionValidationService;
        this.statisticsCalculatorService = statisticsCalculatorService;
    }

    @Override
    public void registerTransaction(Transaction transaction) {
        transactionValidationService.validate(transaction);
        statisticsCalculatorService.add(transaction);
    }
}
