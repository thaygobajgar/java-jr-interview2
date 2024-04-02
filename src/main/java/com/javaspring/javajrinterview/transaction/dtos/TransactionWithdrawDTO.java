package com.javaspring.javajrinterview.transaction.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionWithdrawDTO extends TransactionDTO{
    private String withdraw;
    private String taxes;


}