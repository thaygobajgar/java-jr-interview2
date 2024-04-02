package com.javaspring.javajrinterview.company.dtos;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyCreateDTO{
        private UUID id;

    @NotBlank(message = "Este campo é obrigatório.")
    @Length(min = 5, message = "O nome deve conter pelo menos 5 caracteres.")
    private String name;

    @NotBlank(message = "Este campo é obrigatório.")
    @Email(message = "Insira um e-mail válido!")    
    private String email;

    private String phone;

    @NotBlank(message = "Este campo é obrigatório.")
    private String password;
    
    private String cnpj;

}
