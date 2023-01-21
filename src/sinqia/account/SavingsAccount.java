package sinqia.account;

import java.math.BigDecimal;

public class SavingsAccount extends Account implements IOperations {
	static final BigDecimal INTEREST = BigDecimal.valueOf(0.01);
	
	public SavingsAccount(Long accountNumber) {
		super(accountNumber);
	}
	
	public static BigDecimal getInterest() {
		return INTEREST;
	}

	public BigDecimal calculateInterest(BigDecimal value) {
		return value.multiply(INTEREST);
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
		BigDecimal interest = calculateInterest(amount);

		balance = balance.subtract(amount);
		destination.balance = destination.balance.add(amount);
		destination.balance = destination.balance.add(interest);
	}
}
