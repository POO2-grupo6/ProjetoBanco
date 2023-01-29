package sinqia.account;

import sinqia.exceptions.InsufficientFundsExceptions;

import java.math.BigDecimal;

public class Account {
    private long accountNumber;
    protected BigDecimal balance;

    public Account(long accountNumber) {
        this.accountNumber = accountNumber;
        balance = BigDecimal.ZERO;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void addToBalance (BigDecimal amount){
        balance = balance.add(amount);
    }

    public void removeFromBalance (BigDecimal amount){
        if (amount.compareTo(balance) > 0) {
            throw new InsufficientFundsExceptions();
        }

        balance = balance.subtract(amount);
    }
}
