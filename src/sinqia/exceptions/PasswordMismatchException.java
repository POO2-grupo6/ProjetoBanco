package sinqia.exceptions;

public class PasswordMismatchException extends Exception {
	public String getMessage() {
		return "Senha incorreta.";
	}
}
