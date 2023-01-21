package sinqia.account;

import java.math.BigDecimal;

public class InvestmentAccount extends Account implements IInterest {
	private BigDecimal interest;
	public InvestmentAccount(Long accountNumber, BigDecimal interest) {
		super(accountNumber);
		this.interest = interest;
	}
	
    public void redeem(Account destination, BigDecimal amount) {
    	
    }

//	public BigDecimal calculateInterest(BigDecimal value) {
//		return value.multiply(INTEREST);
//	}


}
