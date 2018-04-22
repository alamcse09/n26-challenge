package com.n26.challenge.tasks;

import com.n26.challenge.services.StatisticsCalculatorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TransactionsPurgerTask {

    private static final Logger LOGGER = LogManager.getLogger();
    private final StatisticsCalculatorService statisticsCalculatorService;

    @Autowired
    public TransactionsPurgerTask(StatisticsCalculatorService statisticsCalculatorService) {
        this.statisticsCalculatorService = statisticsCalculatorService;
    }

    @Scheduled(fixedRate = 1000)
    public void purgeOldTransactions() {
        LOGGER.debug("Purging old transactions...");
        statisticsCalculatorService.purgeOldTransactions();
    }
}
