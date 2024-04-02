package com.javaspring.javajrinterview.company.dtos;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDTO {
    private UUID id;
    private String name;
    private String email;
    private String phone;
    private String cnpj;
}





