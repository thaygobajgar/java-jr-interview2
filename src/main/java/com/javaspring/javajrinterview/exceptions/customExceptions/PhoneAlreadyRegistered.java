package com.javaspring.javajrinterview.exceptions.customExceptions;

public class PhoneAlreadyRegistered extends RuntimeException {
    public PhoneAlreadyRegistered(String phone) {
        super("O telefone '" + phone + "' já está em uso.");
    }
}