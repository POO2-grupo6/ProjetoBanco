package main.java.model.bank;

public class AccountNotFoundException extends RuntimeException{
    @Override
    public String getMessage() {
        return "Conta não localizada. Nenhuma alteração realizada. Por favor, tente novamente.";
    }
}
