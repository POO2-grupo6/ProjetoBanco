package main.java.view;

import java.math.BigDecimal;

public class CheckingAccountView {
    void showAccountBalance(BigDecimal balance) {
        System.out.format("O saldo da conta-corrente é de R$ %,.2f.", balance);
    }
}
