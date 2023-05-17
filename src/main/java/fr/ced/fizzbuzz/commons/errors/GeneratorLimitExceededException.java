package fr.ced.fizzbuzz.commons.errors;

import org.springframework.http.HttpStatus;

public class GeneratorLimitExceededException extends CommonRestException {

    public GeneratorLimitExceededException(String message, Throwable cause, HttpStatus httpStatus) {
        super(message, cause, httpStatus);
    }
}
