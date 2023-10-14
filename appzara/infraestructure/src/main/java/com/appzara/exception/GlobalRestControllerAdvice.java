package com.appzara.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;

import java.time.DateTimeException;

@RestControllerAdvice
@Slf4j
public class GlobalRestControllerAdvice {

    @ExceptionHandler({ResourceNotFoundException.class})
    public final ResponseEntity<ApiError> resourceNotFoundException(final Exception ex, final WebRequest request) {
        log.info("[GlobalRestControllerAdvice] Exception resourceNotFoundException: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiError(HttpStatus.NOT_FOUND, ex.getLocalizedMessage()));
    }

    @ExceptionHandler({DateTimeException.class})
    public final ResponseEntity<ApiError> handleGenericException(final Exception ex, final WebRequest request) {
        log.info("[GlobalRestControllerAdvice] Exception handleGenericException: {}", ex.getMessage());
        return ResponseEntity.badRequest().body(new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage()));
    }

    @ExceptionHandler({HttpServerErrorException.class})
    public final ResponseEntity<ApiError> handlerInternalServer(final Exception ex, final WebRequest request) {
        log.info("[GlobalRestControllerAdvice] Exception handlerInternalServer: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage()));
    }
}
