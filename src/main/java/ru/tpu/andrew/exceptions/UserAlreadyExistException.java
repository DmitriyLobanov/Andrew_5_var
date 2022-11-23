package ru.tpu.andrew.exceptions;

public class UserAlreadyExistException extends RuntimeException {

    public UserAlreadyExistException(String msg) {
        super(msg);
    }
}
