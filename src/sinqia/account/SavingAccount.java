package sinqia.account;

import java.math.BigDecimal;

public class SavingAccount extends Account {

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
	
}
