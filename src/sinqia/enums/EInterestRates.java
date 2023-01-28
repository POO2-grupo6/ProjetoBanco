package sinqia.enums;

import java.math.BigDecimal;

public enum EInterestRates {
	INVESTMENT_INTEREST_RATE_PJ(BigDecimal.valueOf(0.035)),
    SAVINGS_INTEREST_RATE_PF(BigDecimal.valueOf(0.01)),
    INVESTMENT_INTEREST_RATE_PF(BigDecimal.valueOf(0.015));

	private BigDecimal rate;
	
	EInterestRates(BigDecimal rate) {
		this.rate = rate;
	}

	public BigDecimal getRate() {
		return rate;
	}

	
}

