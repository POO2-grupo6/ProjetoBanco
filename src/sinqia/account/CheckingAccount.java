package sinqia.account;

import sinqia.exceptions.InsufficientFundsExceptions;
import sinqia.interfaces.IAcceptsTransfer;
import sinqia.interfaces.IOperations;

import java.math.BigDecimal;

public class CheckingAccount extends Account implements IOperations, IAcceptsTransfer {
	public CheckingAccount(long accountNumber) {
		super(accountNumber);
	}

	@Override
	public void withdraw(BigDecimal amount) {
		if (amount.compareTo(balance) > 0) {
			throw new InsufficientFundsExceptions();
		}

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
