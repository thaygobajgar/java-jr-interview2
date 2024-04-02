package com.javaspring.javajrinterview.exceptions.customExceptions;

public class ClientNotFoundException  extends RuntimeException {

    public ClientNotFoundException(String message) {
        super(message);
    }
}
