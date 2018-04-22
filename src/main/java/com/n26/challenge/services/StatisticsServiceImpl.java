package com.n26.challenge.services;

import com.n26.challenge.models.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    private final StatisticsCalculatorService statisticsCalculatorService;

    @Autowired
    public StatisticsServiceImpl(StatisticsCalculatorService statisticsCalculatorService) {
        this.statisticsCalculatorService = statisticsCalculatorService;
    }

    @Override
    public Statistics getCurrentStatistics() {
        return statisticsCalculatorService.getCurrentStatistics();
    }
}
