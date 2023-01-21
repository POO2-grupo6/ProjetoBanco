package sinqia.account;

import java.math.BigDecimal;

public class CheckingAccount extends Account implements IOperations {

	public CheckingAccount(Long accountNumber) {
		super(accountNumber);
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
