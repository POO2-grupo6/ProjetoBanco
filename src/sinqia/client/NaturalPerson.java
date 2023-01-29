package sinqia.client;

import java.math.BigDecimal;

import sinqia.account.Account;
import sinqia.account.SavingsAccount;
import sinqia.enums.EInterestRates;

public class NaturalPerson extends Client {
	private Account[] accounts = new Account[3];

	public NaturalPerson() {
	}

	public NaturalPerson(String name) {
		super(name);
	}

	public Account[] getAccounts() {
		return accounts;
	}

	public SavingsAccount getSavingsAccount() {
		return (SavingsAccount) accounts[2];
	}

	public void setSavingsAccount(SavingsAccount savingsAccount) {
		accounts[2] = savingsAccount;
	}

	@Override
	public BigDecimal getInvestmentInterestRate() {
		return EInterestRates.INVESTMENT_INTEREST_RATE_PF.getRate();
	}
}