package main.java.model.bank;

public class AccountAlreadyExistsException extends RuntimeException {
    @Override
    public String getMessage() {
        return "A conta já existe. Nenhuma alteração realizada.";
    }
}
