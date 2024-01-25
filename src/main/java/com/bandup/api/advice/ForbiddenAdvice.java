package com.bandup.api.advice;

import com.bandup.api.exception.ForbiddenException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ForbiddenAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = ForbiddenException.class)
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.FORBIDDEN,
                ex.getMessage()
        );
        return handleExceptionInternal(ex, problemDetail, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }
}
