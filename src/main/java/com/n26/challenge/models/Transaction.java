package com.n26.challenge.models;

public class Transaction {
    private double amount;
    private long timestamp; //Epoch in millis (UTC)

    public Transaction(double amount, long timestamp) {
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return String.format("[Amount = %f, Timestamp = %d]", amount, timestamp);
    }
}
