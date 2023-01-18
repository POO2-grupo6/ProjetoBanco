package main.java.model.client;

import main.java.model.account.DepositAccount;
import main.java.model.bank.AccountAlreadyExistsException;
import main.java.model.bank.DestinationAccountNotADepositAccountException;
import main.java.model.account.InvestmentAccount;
import main.java.model.account.Account;
import main.java.model.account.AccountThatPaysInterest;
import main.java.model.account.CheckingAccount;
import main.java.view.BankView;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class Client extends Person {
    private String name;
    private String registrationId;
    private String password; // encrypted?
    private CheckingAccount checkingAccount;
    private InvestmentAccount investmentAccount;

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

    // deposit
    public void withdraw(Account account, BigDecimal value) {
        account.removeFromBalance(value);
    }

    public void transfer(Account sourceAccount, Account destinationAccount, BigDecimal value) {
        if (!(destinationAccount instanceof DepositAccount)) {
            throw new DestinationAccountNotADepositAccountException();
        }

        if (destinationAccount instanceof AccountThatPaysInterest) {
            AccountThatPaysInterest accountThatPaysInterest = (AccountThatPaysInterest) destinationAccount;
            BigDecimal valueWithInterest = value.multiply(BigDecimal.ONE.add(accountThatPaysInterest.getInterest()));
            sourceAccount.removeFromBalance(value);
            accountThatPaysInterest.addToBalance(valueWithInterest);
        } else {
            sourceAccount.removeFromBalance(value);
            destinationAccount.addToBalance(value);
        }
    }

    public void invest(BigDecimal value) {
        BigDecimal valueWithInterest = value.multiply(BigDecimal.ONE.add(this.investmentAccount.getInterest()));
        this.checkingAccount.removeFromBalance(value);
        this.investmentAccount.addToBalance(valueWithInterest);
    }

    public void withdrawFromInvestment(BigDecimal value) {
        this.withdraw(this.investmentAccount, value);
        this.checkingAccount.addToBalance(value);
    }

    public void openCheckingAccount(int accountNumber) {  // acho que deveria estar em Client, passando o número da conta
        if (this.getCheckingAccount() != null) {
            throw new AccountAlreadyExistsException();
        }

        this.setCheckingAccount(new CheckingAccount(accountNumber));
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

    public void openInvestmentAccount(int accountNumber, BigDecimal interest) {
        if (this.getInvestmentAccount() == null) {
            this.setInvestmentAccount(new InvestmentAccount(accountNumber, interest));
        } else {
            throw new AccountAlreadyExistsException();
        }
    }
}
