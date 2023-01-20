package sinqia.account;

import java.math.BigDecimal;

public class SavingAccount {
	static final BigDecimal INTEREST = BigDecimal.valueOf(0.01);
	
	public BigDecimal setInterest(BigDecimal value) {
		return value.multiply(INTEREST);
	}
}
