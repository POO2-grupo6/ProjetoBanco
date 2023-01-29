package sinqia.enums;

import java.math.BigDecimal;

public enum EOperationTaxes {
	WITHDRAW_TAX_RATE_PJ(BigDecimal.valueOf(0.005)),
	TRANSFER_TAX_RATE_PJ(BigDecimal.valueOf(0.005));

	private final BigDecimal tax;
		
	EOperationTaxes(BigDecimal tax) {
		this.tax = tax;
	}

	public BigDecimal getTax() {
		return tax;
	}
}
