package org.example.schoolsystem.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super("Oh no! Something went wrong! Here is the problem: " + message);
    }
}
