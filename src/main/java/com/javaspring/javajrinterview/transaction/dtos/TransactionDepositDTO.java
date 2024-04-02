package com.javaspring.javajrinterview.transaction.dtos;

import java.util.UUID;

// Esta classe agora herda de TransactionDTO e lida especificamente com depósitos
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionDepositDTO extends TransactionDTO{
    private String deposit; // Novo campo para valor formatado

}