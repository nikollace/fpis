package fon.bg.ac.rs.fpis.trunks.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler
//    public ResponseEntity<GenericResponse> handleExceptions(Exception ex, WebRequest request) {
//        return new ResponseEntity<>(new GenericResponse("Unknown error occurred."), HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @ExceptionHandler({RadnikNotFoundException.class})
    public ResponseEntity<GenericResponse> handleRadnikNotFoundException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(new GenericResponse(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({TokenVerificationFailedException.class})
    public ResponseEntity<GenericResponse> handleTokenVerificationFailedException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(new GenericResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
