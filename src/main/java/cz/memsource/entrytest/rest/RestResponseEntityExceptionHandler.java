package cz.memsource.entrytest.rest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler({WebClientResponseException.Unauthorized.class})
    public ResponseEntity<Object> handleException() {
        return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({Exception.class})
    //TODO: make error codes more fine grained
    public ResponseEntity<String> handleException(Exception ex) {
        return new ResponseEntity<>(ex.getLocalizedMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
