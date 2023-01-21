package sinqia.exceptions;

public class ClientNotFoundException extends Exception {
	@Override
	public String getMessage() {
		return "Cliente não encontrado.";
	}
}
