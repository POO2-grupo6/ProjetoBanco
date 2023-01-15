package main.java.model.bank;

public class ClientCanNotOpenSavingsAccountException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Apenas clientes pessoa física podem abrir conta-poupança. Nenhuma alteração realizada.";
    }
}