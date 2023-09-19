package com.appzara.rest.controller.advice;

import com.appzara.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;

import java.time.DateTimeException;

@RestControllerAdvice
public class GlobalRestControllerAdvice {

    @ExceptionHandler({ResourceNotFoundException.class})
    public final ResponseEntity<GlobalHandlerException> resourceNotFoundException(final Exception ex, final WebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GlobalHandlerException(ex.getMessage()));
    }

    @ExceptionHandler({DateTimeException.class})
    public final ResponseEntity<GlobalHandlerException> handleGenericException(final Exception ex, final WebRequest request) {
        return ResponseEntity.badRequest().body(new GlobalHandlerException("Bad Request"));
    }

    @ExceptionHandler({HttpServerErrorException.class})
    public final ResponseEntity<GlobalHandlerException> handlerInternalServer(final Exception ex, final WebRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GlobalHandlerException("Internal Server Error"));
    }
}
