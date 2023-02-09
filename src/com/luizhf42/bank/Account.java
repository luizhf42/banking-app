package com.luizhf42.bank;

public class Account {
    private final String ownerName;
    private final int id;
    private long balance;

    public Account(String ownerName, int id, long balance) {
        this.ownerName = ownerName;
        this.id = id;
        this.balance = balance;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public int getId() {
        return id;
    }

    public long getBalance() {
        return balance;
    }

    protected void updateBalance(long newBalance) {
        this.balance = newBalance;
    }
}
