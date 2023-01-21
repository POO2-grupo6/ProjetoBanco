package sinqia.account;

import java.math.BigDecimal;

public interface IInterest {

	public static BigDecimal calculateInterest(BigDecimal interestRate, BigDecimal value) {
		return value.multiply(interestRate);
	}
	
}
