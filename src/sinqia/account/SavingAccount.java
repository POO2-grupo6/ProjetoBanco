package sinqia.account;

import java.math.BigDecimal;

public class SavingAccount extends Account implements IOperations {

	static final BigDecimal INTEREST = BigDecimal.valueOf(0.01);
	
	public SavingAccount(Long accountNumber) {
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
		
	}

	@Override
	public void deposit(BigDecimal amount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void transfer(Account destination, BigDecimal amount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void invest(BigDecimal amount) {
		// TODO Auto-generated method stub
		
	}
	
}
