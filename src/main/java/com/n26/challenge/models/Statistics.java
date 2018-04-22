package com.n26.challenge.models;

public final class Statistics {
    private final long count;
    private final double sum;
    private final double avg;
    private final double max;
    private final double min;

    public Statistics(long count, double sum, double avg, double max, double min) {
        this.count = count;
        this.sum = sum;
        this.avg = avg;
        this.max = max;
        this.min = min;
    }

    public long getCount() {
        return count;
    }

    public double getSum() {
        return sum;
    }

    public double getAvg() {
        return avg;
    }

    public double getMax() {
        return max;
    }

    public double getMin() {
        return min;
    }
}
