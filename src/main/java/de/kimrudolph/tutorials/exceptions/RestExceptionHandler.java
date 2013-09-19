package de.kimrudolph.tutorials.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { CatNotFoundException.class })
    protected ResponseEntity<Object> handleMissingCat(
        final RuntimeException exception, final WebRequest request) {

        final String bodyOfResponse = "Cat '" + exception.getMessage()
            + "' has not been found. Maybe you misspelled the name?";

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);

        return handleExceptionInternal(exception, bodyOfResponse, headers,
            HttpStatus.NOT_FOUND, request);
    }

}
