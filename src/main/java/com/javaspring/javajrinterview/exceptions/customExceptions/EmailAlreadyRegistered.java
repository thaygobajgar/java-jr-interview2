package com.javaspring.javajrinterview.exceptions.customExceptions;

public class EmailAlreadyRegistered extends RuntimeException {
    public EmailAlreadyRegistered(String email) {
        super("O email '" + email + "' já está em uso.");
    }
}