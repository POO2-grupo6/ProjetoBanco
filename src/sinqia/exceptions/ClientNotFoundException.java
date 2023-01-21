package sinqia.exceptions;

public class ClientNotFoundException extends Exception {
	public String getMessage() {
		return "Cliente não encontrado.";
	}
}
