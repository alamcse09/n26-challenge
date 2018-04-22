package com.n26.challenge.services;

import com.n26.challenge.models.Transaction;
import org.junit.Test;

import java.time.Instant;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
public class TransactionValidationServiceImplTest {

    @Test
    public void testVeryRecentTransaction() {
        TransactionValidationService service = getServiceWithFixedNowTimestamp(60_000);

        Transaction veryRecentTransaction = new Transaction(0,59_999);
        assertThat(service.isValid(veryRecentTransaction), is(true));
    }

    @Test
    public void testBarelyRecentTransaction() {
        TransactionValidationService service = getServiceWithFixedNowTimestamp(60_000);

        Transaction barelyRecentTransaction = new Transaction(0,0);
        assertThat(service.isValid(barelyRecentTransaction), is(true));
    }

    @Test
    public void testOldTransaction() {
        TransactionValidationService service = getServiceWithFixedNowTimestamp(60_001);

        Transaction oldTransaction = new Transaction(0,0);
        assertThat(service.isValid(oldTransaction), is(false));
    }

    private TransactionValidationService getServiceWithFixedNowTimestamp(long nowTimestamp) {
        return new TransactionValidationServiceImpl(null) {
            @Override
            protected Instant now() {
                return Instant.ofEpochMilli(nowTimestamp);
            }
        };
    }
}