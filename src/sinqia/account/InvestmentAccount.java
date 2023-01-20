package sinqia.account;

import java.math.BigDecimal;

public class InvestmentAccount extends Account {

	static final BigDecimal INTEREST_RATE_PF = BigDecimal.valueOf(0.015);
	static final BigDecimal INTEREST_RATE_PJ = BigDecimal.valueOf(0.035);
	
	public InvestmentAccount(Long accountNumber) {
		super(accountNumber);
	}

	public static BigDecimal getInterestPf() {
		return INTEREST_RATE_PF;
	}

	public static BigDecimal getInterestPj() {
		return INTEREST_RATE_PJ;
	}
	
	public BigDecimal calculateInterestPF(BigDecimal value) {
		return value.multiply(INTEREST_RATE_PF);
	}
	
	public BigDecimal calculateInterestPJ(BigDecimal value) {
		return value.multiply(INTEREST_RATE_PJ);
	}
	
}
