package com.javaspring.javajrinterview.transaction.dtos;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionWithdrawDTO extends TransactionDTO{
    private String withdraw; // Novo campo para valor formatado
    private String taxes; // Novo campo para impostos formatados

    // Remova os métodos getFormattedValue e getFormattedTaxes anteriores
}