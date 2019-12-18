package com.example.gyak3.domestictransaction;

import java.util.UUID;

public class DomesticTransaction {
    private UUID transactionId;
    private String senderName;
    private String hostName;
    private String hostAccountNumber;
    private long amount;

    public DomesticTransaction() {
    }

    public DomesticTransaction(UUID transactionId, String senderName, String hostName, String hostAccountNumber, long amount) {
        this.transactionId = transactionId;
        this.senderName = senderName;
        this.hostName = hostName;
        this.hostAccountNumber = hostAccountNumber;
        this.amount = amount;
    }

    public UUID getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(UUID transactionId) {
        this.transactionId = transactionId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getHostAccountNumber() {
        return hostAccountNumber;
    }

    public void setHostAccountNumber(String hostAccountNumber) {
        this.hostAccountNumber = hostAccountNumber;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "DomesticTransaction{" +
                "transactionId=" + transactionId +
                ", senderName='" + senderName + '\'' +
                ", hostName='" + hostName + '\'' +
                ", hostAccountNumber='" + hostAccountNumber + '\'' +
                ", amount=" + amount +
                '}';
    }

    public DomesticTransaction id(UUID id) {
        this.transactionId = id;
        return this;
    }
}
