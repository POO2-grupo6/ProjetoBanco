package sinqia.account;

import java.math.BigDecimal;

public class InvestmentAccount extends Account implements IInterest {
	private BigDecimal interestRate;
	public InvestmentAccount(long accountNumber, BigDecimal interestRate) {
		super(accountNumber);
		this.interestRate = interestRate;
	}
	
    public void redeem(Account destination, BigDecimal amount) {
    	
    }

	public BigDecimal calculateInterest(BigDecimal value) {
		return value.multiply(interestRate);
	}


}
