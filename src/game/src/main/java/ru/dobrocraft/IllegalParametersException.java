package ru.dobrocraft;

public class IllegalParametersException extends RuntimeException {
    public IllegalParametersException(String message) {
        super(message);
    }
}