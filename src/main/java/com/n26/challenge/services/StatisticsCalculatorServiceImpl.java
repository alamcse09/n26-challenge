package com.n26.challenge.services;

import com.n26.challenge.models.Statistics;
import com.n26.challenge.models.Transaction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.PriorityQueue;

/**
 * Stateful service. Very important that this should be a Singleton
 */
@Service
public class StatisticsCalculatorServiceImpl implements StatisticsCalculatorService {

    private static final Logger LOGGER = LogManager.getLogger();
    private final PriorityQueue<Transaction> lastTransactions =
            new PriorityQueue<>(Comparator.comparingLong(Transaction::getTimestamp));
    private final TransactionValidationService transactionValidationService;

    private Statistics currentStatistics = new Statistics(0, 0, 0, 0, 0);

    @Autowired
    public StatisticsCalculatorServiceImpl(TransactionValidationService transactionValidationService) {
        this.transactionValidationService = transactionValidationService;
    }

    @Override
    public synchronized void add(Transaction transaction) {
        lastTransactions.add(transaction);

        double transactionAmount = transaction.getAmount();
        long newCount = currentStatistics.getCount() + 1;
        double newMaximum = Math.max(currentStatistics.getMax(), transactionAmount);
        double newMinimum = Math.min(currentStatistics.getMin(), transactionAmount);
        double newAverage = (currentStatistics.getAverage() * currentStatistics.getCount() + transactionAmount)
            / newCount;
        double newSum = currentStatistics.getSum() + transactionAmount;

        currentStatistics = new Statistics(newCount, newSum, newAverage, newMaximum, newMinimum);
    }

    @Override
    public synchronized void purgeOldTransactions() {
        Transaction oldestTransaction = lastTransactions.peek();

        while (oldestTransaction != null && !transactionValidationService.isValid(oldestTransaction)) {
            LOGGER.info("Purging old transaction: {}", oldestTransaction);
            lastTransactions.poll();
            oldestTransaction = lastTransactions.peek();
        }
        recalculateStatistics();
    }

    @Override
    public Statistics getCurrentStatistics() {
        return currentStatistics;
    }

    private void recalculateStatistics() {
        DoubleSummaryStatistics newStatistics = lastTransactions
            .stream()
            .mapToDouble(Transaction::getAmount)
            .summaryStatistics();

        int size = lastTransactions.size();
        double sum = newStatistics.getSum();
        double average = newStatistics.getAverage();
        double max = newStatistics.getMax() != Double.NEGATIVE_INFINITY ? newStatistics.getMax() : 0;
        double min = newStatistics.getMin() != Double.POSITIVE_INFINITY ? newStatistics.getMin() : 0;

        currentStatistics = new Statistics(size, sum, average, max, min);
    }
}
