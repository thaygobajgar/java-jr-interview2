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

    // Método para criar uma taxa associada a uma transação
    public TaxEntity createTax(TransactionEntity transaction, double taxRate) {
        // Calcula o valor da taxa baseado no valor da transação e na taxa
        double taxAmount = transaction.getValue() * taxRate;

        // Cria uma nova entidade TaxEntity
        TaxEntity tax = new TaxEntity(transaction, taxAmount);

        // Salva a taxa no banco de dados
        return taxRepository.save(tax);
    }
}