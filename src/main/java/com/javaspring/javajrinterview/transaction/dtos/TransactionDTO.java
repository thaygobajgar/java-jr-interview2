package com.javaspring.javajrinterview.transaction.dtos;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {
    private UUID id;
    private UUID companyId;
    private UUID clientId;
    private String type;
    private Double value;
    
}
