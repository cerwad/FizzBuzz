package fr.ced.fizzbuzz.commons.errors;

import org.springframework.http.HttpStatus;

import java.net.URI;

public class BadArgumentsException extends CommonRestException {

    public BadArgumentsException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
        this.category = "Bad Arguments";
        this.type = URI.create("https://api.bookmarks.com/errors/bad-argument");
    }
}
