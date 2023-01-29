package sinqia.exceptions;

public class InsufficientFundsExceptions extends RuntimeException {
    @Override
    public String getMessage() {
        return "Saldo Insuficiente!";
    }
}
