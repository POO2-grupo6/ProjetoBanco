package sinqia.account;

import java.math.BigDecimal;

public class InvestmentAccount extends Account {

	static final BigDecimal INTEREST_PF = BigDecimal.valueOf(0.015);
	static final BigDecimal INTEREST_PJ = BigDecimal.valueOf(0.035);
	
	public InvestmentAccount(Long accountNumber) {
		super(accountNumber);
	}
}
