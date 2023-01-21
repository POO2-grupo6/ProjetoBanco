package sinqia.account;

import java.math.BigDecimal;

public class CheckingAccount extends Account implements IOperations {
	public CheckingAccount(Long accountNumber) {
		super(accountNumber);
	}

	@Override
	public void withdraw(BigDecimal amount) {
		// TODO Auto-generated method stub
		balance = balance.subtract(amount);
	}

	@Override
	public void deposit(BigDecimal amount) {
		// TODO Auto-generated method stub
		balance = balance.add(amount);
	}

	@Override
	public void transfer(Account destination, BigDecimal amount) {
		// TODO Auto-generated method stub
		balance = balance.subtract(amount);
		destination.balance = destination.balance.add(amount);
	}

	@Override
	public void invest(InvestmentAccount destination, BigDecimal amount) {
		// TODO Auto-generated method stub
//		BigDecimal interest = destination.calculateInterest(amount);
//
//		balance = balance.subtract(amount);
//		destination.balance = destination.deposit(); // balance.add(amount);
//		destination.balance = destination.balance.add(interest);
	}
}
