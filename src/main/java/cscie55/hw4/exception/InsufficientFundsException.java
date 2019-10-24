package cscie55.hw4.exception;

import cscie55.hw4.api.Account;

public class InsufficientFundsException extends Exception
{
    public InsufficientFundsException(Account account, long withdrawal)
    {
        super(String.format("Attempt to withdraw %d from %s", withdrawal, account));
    }
}
