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

    @Override
    public void addAccount(Account account) throws DuplicateAccountException {

        if(accountMap.keySet().contains(account.getId())){
            throw new DuplicateAccountException(account.getId());
        }
        accountMap.put(account.getId(), account);
    }

    @Override
    public void transferWithoutLocking(int fromId, int toId, long amount) throws InsufficientFundsException {
        Account fromAcct = accountMap.get(fromId);
        Account toAcct = accountMap.get(toId);
        fromAcct.withdraw(amount);
        toAcct.deposit(amount);
    }

    @Override
    public void transferLockingBank(int fromId, int toId, long amount) throws InsufficientFundsException {

    }

    @Override
    public void transferLockingAccounts(int fromId, int toId, long amount) throws InsufficientFundsException {

    }

    @Override
    public long getTotalBalances() {
        return accountMap.values()
                .stream()
                .map(account -> account.getBalance())
                .reduce(0L, Long::sum);
    }

    @Override
    public int getNumberOfAccounts() {
        return accountMap.size();
    }
}
