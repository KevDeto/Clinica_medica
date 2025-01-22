package clinica.exceptions;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
	NOT_FOUND(HttpStatus.NOT_FOUND),
	BAD_REQUEST(HttpStatus.BAD_REQUEST),
	NO_CONTENT(HttpStatus.NO_CONTENT),
	CONFLICT(HttpStatus.CONFLICT),
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR);

    private final HttpStatus status;

    ErrorCode(HttpStatus status) {
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
