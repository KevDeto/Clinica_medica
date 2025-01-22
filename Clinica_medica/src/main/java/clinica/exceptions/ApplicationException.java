package clinica.exceptions;

public class ApplicationException extends RuntimeException {
	private final ErrorCode errorCode;

	public ApplicationException(ErrorCode errorCode, String mensaje, Throwable causa) {
		super(mensaje, causa);
		this.errorCode = errorCode;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}
}
