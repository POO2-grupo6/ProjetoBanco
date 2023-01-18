package main.java.model.client;

import main.java.model.account.Account;
import main.java.model.account.AccountThatPaysInterest;
import main.java.model.account.DepositAccount;
import main.java.model.bank.DestinationAccountNotADepositAccountException;

import java.math.BigDecimal;

public class Person {
    public void deposit(Account account, BigDecimal value) {
        if (!(account instanceof DepositAccount)) {
            throw new DestinationAccountNotADepositAccountException();
        }

        if (account instanceof AccountThatPaysInterest) {
            AccountThatPaysInterest accountThatPaysInterest = (AccountThatPaysInterest) account;
            BigDecimal valueWithInterest = value.multiply(BigDecimal.ONE.add(accountThatPaysInterest.getInterest()));
            accountThatPaysInterest.addToBalance(valueWithInterest);
        } else {
            account.addToBalance(value);
        }
    }
}
