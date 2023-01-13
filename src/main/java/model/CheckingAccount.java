package main.java.model;

import java.math.BigDecimal;

public class CheckingAccount extends Account {
    BigDecimal overdraftLimit = BigDecimal.ZERO;
}
