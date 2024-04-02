package com.javaspring.javajrinterview.tax.dtos;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaxDTO {
    private UUID id;
    private UUID transactionId;
    private double taxAmount;
}