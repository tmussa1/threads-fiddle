package cscie55.hw4.api;

import cscie55.hw4.exception.InsufficientFundsException;

public interface Account
{
    int getId();
    long getBalance();
    void deposit(long amount);
    void withdraw(long amount) throws InsufficientFundsException;
}
