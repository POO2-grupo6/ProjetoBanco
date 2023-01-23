package sinqia.client;

import java.math.BigDecimal;

import sinqia.account.Account;
import sinqia.account.SavingsAccount;

public class NaturalPerson extends Client {
	private String cpf;
	private Account[] accounts = new Account[3];
	private static final BigDecimal SAVINGS_INTEREST_RATE = BigDecimal.valueOf(0.01);
	private static final BigDecimal INVESTMENT_INTEREST_RATE = BigDecimal.valueOf(0.015);

	public NaturalPerson() {
	}

	public NaturalPerson(String name, String cpf) {
		super(name);
		this.cpf = cpf;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Account[] getAccounts() {
		return accounts;
	}

	public void setAccounts(Account[] accounts) {
		this.accounts = accounts;
	}

	public static BigDecimal getSavingsInterestRate() {
		return SAVINGS_INTEREST_RATE;
	}

	@Override
	public BigDecimal getInvestmentInterestRate() {
		return INVESTMENT_INTEREST_RATE;
	}

	public SavingsAccount getSavingsAccount() {
		return (SavingsAccount) accounts[2];
	}

	public void setSavingsAccount(SavingsAccount savingsAccount) {
		accounts[2] = savingsAccount;
	}
}