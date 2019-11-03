package cscie55.hw4.impl;

import cscie55.hw4.api.Account;
import cscie55.hw4.exception.InsufficientFundsException;

import java.util.Objects;

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

    /**
     * Deposits a positive amount
     * @param amount
     */
    @Override
    public void deposit(long amount) {
        if(amount <= 0){
            throw new IllegalArgumentException();
        }

        balance += amount;
    }

    /**
     * Withdraws amount if account has sufficient balance
     * @param amount
     * @throws InsufficientFundsException
     */
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountImpl account = (AccountImpl) o;
        return accountId == account.accountId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId);
    }
}
