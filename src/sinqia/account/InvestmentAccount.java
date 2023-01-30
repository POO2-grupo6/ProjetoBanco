package sinqia.account;

import java.math.BigDecimal;

import sinqia.exceptions.InsufficientFundsExceptions;
import sinqia.interfaces.IPaysInterest;

public class InvestmentAccount extends Account implements IPaysInterest {
	private BigDecimal interestRate;

	public InvestmentAccount(long accountNumber, BigDecimal interestRate) {
		super(accountNumber);
		this.interestRate = interestRate;
	}
	
    public void redeem(CheckingAccount destination, BigDecimal amount) {
		if (amount.compareTo(balance) > 0) {
			throw new InsufficientFundsExceptions();
		}
    	
		balance = balance.subtract(amount);
		destination.balance = destination.balance.add(amount);
    }

	@Override
	public BigDecimal calculateInterest(BigDecimal value) {
		return value.multiply(interestRate);
	}
}
