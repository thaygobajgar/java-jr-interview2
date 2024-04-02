package com.javaspring.javajrinterview.tax;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaspring.javajrinterview.transaction.TransactionEntity;

import lombok.Data;

@Data
@Service
public class TaxService {

    @Autowired
    private TaxRepository taxRepository;

    public TaxEntity createTax(TransactionEntity transaction, double taxRate) {
        double taxAmount = transaction.getValue() * taxRate;

        TaxEntity tax = new TaxEntity(transaction, taxAmount);

        return taxRepository.save(tax);
    }
}