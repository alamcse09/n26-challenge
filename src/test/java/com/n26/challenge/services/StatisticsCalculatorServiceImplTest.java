package com.n26.challenge.services;

import com.n26.challenge.models.Statistics;
import com.n26.challenge.models.Transaction;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class StatisticsCalculatorServiceImplTest {

    @Test
    public void testAddTransactions() {
        StatisticsCalculatorService service = new StatisticsCalculatorServiceImpl(null);

        addTransactions(service, 1.5, 2, 2.5);

        Statistics currentStatistics = service.getCurrentStatistics();
        assertThat(currentStatistics.getCount(), is(3L));
        assertThat(currentStatistics.getAvg(), is(2.0));
        assertThat(currentStatistics.getMax(), is(2.5));
        assertThat(currentStatistics.getMin(), is(1.5));
        assertThat(currentStatistics.getSum(), is(6.0));
    }

    @Test
    public void testPurgeOldTransactions() {
        StatisticsCalculatorService service = new StatisticsCalculatorServiceImpl(stubValidator());

        addTransactions(service, 1.5, 2);
        addInvalidTransactions(service, 777, 888, 999);
        addTransactions(service, 2.5);

        service.purgeOldTransactions();

        Statistics currentStatistics = service.getCurrentStatistics();
        assertThat(currentStatistics.getCount(), is(3L));
        assertThat(currentStatistics.getAvg(), is(2.0));
        assertThat(currentStatistics.getMax(), is(2.5));
        assertThat(currentStatistics.getMin(), is(1.5));
        assertThat(currentStatistics.getSum(), is(6.0));
    }

    private TransactionValidationService stubValidator() {
        return new TransactionValidationServiceImpl(null) {
            @Override
            public boolean isValid(Transaction transaction) {
                return transaction.getTimestamp() > 0;
            }
        };
    }

    private void addTransactions(StatisticsCalculatorService service, double... amounts) {
        Arrays.stream(amounts).forEach(amount -> {
            Transaction transaction = new Transaction(amount, 1);
            service.add(transaction);
        });
    }

    private void addInvalidTransactions(StatisticsCalculatorService service, double... amounts) {
        Arrays.stream(amounts).forEach(amount -> {
            Transaction transaction = new Transaction(amount, 0);
            service.add(transaction);
        });
    }
}