package sinqia.account;

import java.math.BigDecimal;

public class Account {
    private long accountNumber;
    protected BigDecimal balance;

    public Account(long accountNumber) {
        this.accountNumber = accountNumber;
        balance = BigDecimal.ZERO;
    }

    public void addToBalance (BigDecimal value){
        balance = balance.add(value);
    }

    public void removeFromBalance (BigDecimal value){
        balance = balance.subtract(value);
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

}