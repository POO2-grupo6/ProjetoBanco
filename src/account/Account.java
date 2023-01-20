package account;

import java.math.BigDecimal;

public class Account {
    private Long accountNumber;
    private BigDecimal balance;

    public Account(Long accountNumber) {
        this.accountNumber = accountNumber;
        balance = BigDecimal.ZERO;
    }

    public void addToBalance (BigDecimal value){
        balance = balance.add(value);
    }

    public void removeFromBalance (BigDecimal value){
        balance = balance.subtract(value);
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
