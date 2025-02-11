package clinica.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import clinica.model.payload.MensajeResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<MensajeResponse> handleResourceNotFound(ApplicationException ex) {
        return ResponseEntity.status(ex.getErrorCode().getStatus())
                .body(MensajeResponse.builder()
                        .mensaje(ex.getMessage())
                        .objeto(null)
                        .build());
    }
}
