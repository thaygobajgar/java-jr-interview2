package com.javaspring.javajrinterview.exceptions;

import java.util.List;
import java.util.ArrayList;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.javaspring.javajrinterview.exceptions.customExceptions.ClientNotFoundException;
import com.javaspring.javajrinterview.exceptions.customExceptions.CompanyNotFoundException;
import com.javaspring.javajrinterview.exceptions.customExceptions.EmailAlreadyRegistered;
import com.javaspring.javajrinterview.exceptions.customExceptions.InsufficientBalanceException;
import com.javaspring.javajrinterview.exceptions.customExceptions.PhoneAlreadyRegistered;
import com.javaspring.javajrinterview.exceptions.dtos.ErrorMessageDTO;

@ControllerAdvice
public class GlobalExceptionHandler {
    private MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ErrorMessageDTO> dto = new ArrayList<>();

        e.getBindingResult().getFieldErrors().forEach(err -> {
            String message = messageSource.getMessage(err, LocaleContextHolder.getLocale());
            ErrorMessageDTO error = new ErrorMessageDTO(message, err.getField());
            dto.add(error);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<ErrorMessageDTO> handleInsufficientBalanceException(InsufficientBalanceException e) {
        ErrorMessageDTO errorMessageDTO = new ErrorMessageDTO(e.getMessage(), "value");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessageDTO);
    }
    
    @ExceptionHandler(CompanyNotFoundException.class)
    public ResponseEntity<ErrorMessageDTO> handleCompanyNotFoundException(CompanyNotFoundException e) {
        ErrorMessageDTO errorMessageDTO = new ErrorMessageDTO(e.getMessage(), "companyId");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessageDTO);
    }
    
    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<ErrorMessageDTO> handleClientNotFoundException(ClientNotFoundException e) {
        ErrorMessageDTO errorMessageDTO = new ErrorMessageDTO(e.getMessage(), "clientId");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessageDTO);
    }

    @ExceptionHandler(EmailAlreadyRegistered.class)
    public ResponseEntity<ErrorMessageDTO> handleEmailAlreadyRegistered(EmailAlreadyRegistered e) {
        ErrorMessageDTO errorMessageDTO = new ErrorMessageDTO(e.getMessage(), "email");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessageDTO);
    }

    @ExceptionHandler(PhoneAlreadyRegistered.class)
    public ResponseEntity<ErrorMessageDTO> handlePhoneAlreadyRegistered(PhoneAlreadyRegistered e) {
        ErrorMessageDTO errorMessageDTO = new ErrorMessageDTO(e.getMessage(), "phone");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessageDTO);
    }
    
    public GlobalExceptionHandler(MessageSource message) {
        this.messageSource = message;
    }
}