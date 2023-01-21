package sinqia.account;

import java.math.BigDecimal;

public class InvestmentAccount extends Account implements IInterest {

	public InvestmentAccount(Long accountNumber) {
		super(accountNumber);
	}
	
    public void redeem(Account destination, BigDecimal amount) {
    	
    };
	
}
