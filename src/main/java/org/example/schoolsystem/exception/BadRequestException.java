package org.example.schoolsystem.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super("Oh no! Something went wrong! Here is the problem: " + message);
    }
}
