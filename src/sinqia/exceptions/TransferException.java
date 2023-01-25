package sinqia.exceptions;

public class TransferException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Você não pode transferir para a mesma conta!";
    }
}
