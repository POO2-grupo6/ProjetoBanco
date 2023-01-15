package main.java.model.client;

import main.java.model.account.DepositAccount;
import main.java.model.bank.DestinationAccountNotADepositAccountException;
import main.java.model.account.InvestmentAccount;
import main.java.model.account.Account;
import main.java.model.account.AccountThatPaysInterest;
import main.java.model.account.CheckingAccount;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class Client {
    String name;
    String registrationId;
    private String password; // encrypted?
    // birthday?
    CheckingAccount checkingAccount;
    InvestmentAccount investmentAccount;

    protected Client(String name, String registrationId, String password) {
        this.name = name;
        this.registrationId = registrationId;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public CheckingAccount getCheckingAccount() {
        return checkingAccount;
    }

    public void setCheckingAccount(CheckingAccount checkingAccount) {
        this.checkingAccount = checkingAccount;
    }

    public InvestmentAccount getInvestmentAccount() {
        return investmentAccount;
    }

    public void setInvestmentAccount(InvestmentAccount investmentAccount) {
        this.investmentAccount = investmentAccount;
    }

    public void deposit(Account account, BigDecimal value) {
        if (!(account instanceof DepositAccount)) {
            throw new DestinationAccountNotADepositAccountException();
        }

        if (account instanceof AccountThatPaysInterest) {
            AccountThatPaysInterest accountThatPaysInterest = (AccountThatPaysInterest) account;
            BigDecimal valueWithInterest = value.multiply(accountThatPaysInterest.getInterest());
            accountThatPaysInterest.addToBalance(valueWithInterest);
        } else {
            account.addToBalance(value);
        }
    }

    public void withdraw(Account account, BigDecimal value) {
        account.removeFromBalance(value);
    }

    public void transfer(Account sourceAccount, Account destinationAccount, BigDecimal value) {
        sourceAccount.removeFromBalance(value);
        destinationAccount.addToBalance(value);
    }

    public void invest(BigDecimal value) {
        this.transfer(this.checkingAccount, this.investmentAccount, value);
    }

    public void withdrawFromInvestment(BigDecimal value) {
        this.withdraw(this.investmentAccount, value);
    }

    public BigDecimal getBalanceFromAccount(Account account) {
        return account.getBalance();
    }

    public BigDecimal getTotalBalance() {
        BigDecimal totalBalance = BigDecimal.ZERO;
        if (this.checkingAccount != null) {
            totalBalance = totalBalance.add(this.checkingAccount.getBalance());
        }

        if (this.investmentAccount != null) {
            totalBalance = totalBalance.add(this.investmentAccount.getBalance());
        }

        return totalBalance;
    }

    public boolean passwordIsEqualTo(String string) {
        return this.password.equals(string);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(registrationId, client.registrationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registrationId);
    }
}
