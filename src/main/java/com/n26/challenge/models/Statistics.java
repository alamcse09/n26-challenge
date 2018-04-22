package com.n26.challenge.models;

public final class Statistics {
    private final long count;
    private final double sum;
    private final double average;
    private final double max;
    private final double min;

    public Statistics(long count, double sum, double average, double max, double min) {
        this.count = count;
        this.sum = sum;
        this.average = average;
        this.max = max;
        this.min = min;
    }

    public long getCount() {
        return count;
    }

    public double getSum() {
        return sum;
    }

    public double getAverage() {
        return average;
    }

    public double getMax() {
        return max;
    }

    public double getMin() {
        return min;
    }
}
