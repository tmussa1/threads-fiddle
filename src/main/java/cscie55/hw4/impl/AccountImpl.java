package cscie55.hw4.impl;

import cscie55.hw4.api.Account;
import cscie55.hw4.exception.InsufficientFundsException;

public class AccountImpl implements Account {

    private int accountId;
    private long balance;

    public AccountImpl(int accountId) {
        this.accountId = accountId;
    }

    @Override
    public int getId() {
        return accountId;
    }

    @Override
    public long getBalance() {
        return balance;
    }

    @Override
    public void deposit(long amount) {
        if(amount <= 0){
            throw new IllegalArgumentException();
        }

        balance += amount;
    }

    @Override
    public void withdraw(long amount) throws InsufficientFundsException {

        if(amount <= 0){
            throw new IllegalArgumentException();
        }

        if(balance - amount < 0){
            throw new InsufficientFundsException(this, amount);
        }
        balance -= amount;
    }
}
