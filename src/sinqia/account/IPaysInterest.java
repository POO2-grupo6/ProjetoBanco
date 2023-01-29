package sinqia.account;

import java.math.BigDecimal;

public interface IPaysInterest {
	BigDecimal calculateInterest(BigDecimal value);
}
