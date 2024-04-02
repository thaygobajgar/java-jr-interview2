package com.javaspring.javajrinterview.client;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity(name = "clients")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Pattern(regexp = "^[0-9]{11}$", message = "CPF inválido")
    private String cpf;

    @Column(nullable = false, unique = true)
    private String email;


    @NotBlank(message = "O número de celular é obrigatório.")
    @Pattern(regexp = "^\\+?\\d{1,3}?\\d{2}\\d{4,5}\\d{4}$", message = "Número de celular inválido.")
    @Column(unique = true)
    private String phone;

    @Column(nullable = false)
    private String password;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;
}
