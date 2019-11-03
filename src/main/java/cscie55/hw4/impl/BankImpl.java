package cscie55.hw4.impl;

import cscie55.hw4.api.Account;
import cscie55.hw4.api.Bank;
import cscie55.hw4.exception.DuplicateAccountException;
import cscie55.hw4.exception.InsufficientFundsException;

import java.util.HashMap;
import java.util.Map;

public class BankImpl implements Bank {

    private Map<Integer, Account> accountMap;

    public BankImpl() {
        this.accountMap = new HashMap<>();
    }

    /**
     * Adds account to the map
     * @param account
     * @throws DuplicateAccountException
     */
    @Override
    public void addAccount(Account account) throws DuplicateAccountException {

        if(accountMap.keySet().contains(account.getId())){
            throw new DuplicateAccountException(account.getId());
        }
        accountMap.put(account.getId(), account);
    }

    /**
     * Erroneous transfer without locking
     * @param fromId
     * @param toId
     * @param amount
     * @throws InsufficientFundsException
     */
    @Override
    public void transferWithoutLocking(int fromId, int toId, long amount) throws InsufficientFundsException {
        Account fromAcct = accountMap.get(fromId);
        Account toAcct = accountMap.get(toId);
        fromAcct.withdraw(amount);
        toAcct.deposit(amount);
    }

    /**
     * Locks bank
     * @param fromId
     * @param toId
     * @param amount
     * @throws InsufficientFundsException
     */
    @Override
    public void transferLockingBank(int fromId, int toId, long amount) throws InsufficientFundsException {
        synchronized (this){
            Account fromAcct = accountMap.get(fromId);
            Account toAcct = accountMap.get(toId);
            fromAcct.withdraw(amount);
            toAcct.deposit(amount);
        }
    }

    /**
     * Locks accounts one after another
     * @param fromId
     * @param toId
     * @param amount
     * @throws InsufficientFundsException
     */
    @Override
    public void transferLockingAccounts(int fromId, int toId, long amount) throws InsufficientFundsException {
        Account fromAcct = accountMap.get(fromId);
        Account toAcct = accountMap.get(toId);
        Account lock1, lock2;

        if(fromAcct.getId() < toAcct.getId()){
            lock1 = fromAcct;
            lock2 = toAcct;
        } else {
            lock1 = toAcct;
            lock2 = fromAcct;
        }

        synchronized (lock1){
            synchronized (lock2){
                fromAcct.withdraw(amount);
                toAcct.deposit(amount);
            }
        }
    }

    /**
     * Computes total balance of all accounts
     * @return - balance
     */
    @Override
    public long getTotalBalances() {
        return accountMap.values()
                .stream()
                .map(account -> account.getBalance())
                .reduce(0L, Long::sum);
    }

    /**
     * Gets all accounts
     * @return - number of accounts
     */
    @Override
    public int getNumberOfAccounts() {
        return accountMap.size();
    }
}
