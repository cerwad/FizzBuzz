package fr.ced.fizzbuzz.config;

import fr.ced.fizzbuzz.commons.errors.CommonRestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CommonRestException.class)
    ProblemDetail handleCommonRestException(CommonRestException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(e.getHttpStatus(), e.getMessage());
        problemDetail.setTitle(e.getTitle());
        problemDetail.setType(e.getType());
        problemDetail.setProperty("errorCategory", e.getCategory());
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }


    @ExceptionHandler(Throwable.class)
    ProblemDetail handleGenericException(Throwable t) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, t.getMessage());
        problemDetail.setTitle("Unexpected error");
        problemDetail.setType(URI.create("https://api.bookmarks.com/errors/internal-error"));
        problemDetail.setProperty("errorCategory", "Generic");
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }
}