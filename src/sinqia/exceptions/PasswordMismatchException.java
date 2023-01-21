package sinqia.exceptions;

public class PasswordMismatchException extends Exception {
	@Override
	public String getMessage() {
		return "Senha incorreta.";
	}
}
