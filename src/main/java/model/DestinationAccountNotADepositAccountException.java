package main.java.model;

public class DestinationAccountNotADepositAccountException extends RuntimeException {
    @Override
    public String getMessage() {
        return "A conta de destino não aceita depósitos. Nenhuma alteração foi realizada.";
    }
}
