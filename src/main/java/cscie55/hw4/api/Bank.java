package cscie55.hw4.api;

import cscie55.hw4.exception.DuplicateAccountException;
import cscie55.hw4.exception.InsufficientFundsException;

public interface Bank
{
    void addAccount(Account account) throws DuplicateAccountException;
    void transferWithoutLocking(int fromId, int toId, long amount) throws InsufficientFundsException;
    void transferLockingBank(int fromId, int toId, long amount) throws InsufficientFundsException;
    void transferLockingAccounts(int fromId, int toId, long amount) throws InsufficientFundsException;
    long getTotalBalances();
    int  getNumberOfAccounts();
}
