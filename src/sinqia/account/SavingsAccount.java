package sinqia.account;

import sinqia.enums.EInterestRates;

import java.math.BigDecimal;

public class SavingsAccount extends Account implements IOperations, IPaysInterest {
	public SavingsAccount(long accountNumber) {
		super(accountNumber);
	}

	@Override
	public BigDecimal calculateInterest(BigDecimal value) {
		return value.multiply(EInterestRates.SAVINGS_INTEREST_RATE_PF.getRate());
	}

	@Override
	public void withdraw(BigDecimal amount) {
		balance = balance.subtract(amount);
	}

	@Override
	public void deposit(BigDecimal amount) {
		balance = balance.add(amount);
	}

	@Override
	public void transfer(Account destination, BigDecimal amount) {
		balance = balance.subtract(amount);
		destination.balance = destination.balance.add(amount);
	}

	@Override
	public void invest(InvestmentAccount destination, BigDecimal amount) {
		BigDecimal interest = destination.calculateInterest(amount);

		balance = balance.subtract(amount);
		destination.balance = destination.balance.add(amount);
		destination.balance = destination.balance.add(interest);
	}
}
