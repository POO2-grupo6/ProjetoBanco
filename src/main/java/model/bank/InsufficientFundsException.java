package main.java.model.bank;

public class InsufficientFundsException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Saldo insuficiente. Nenhuma alteração realizada.";
    }
}
