package com.javaspring.javajrinterview.transaction;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.javaspring.javajrinterview.client.ClientEntity;
import com.javaspring.javajrinterview.client.ClientRepository;
import com.javaspring.javajrinterview.company.CompanyEntity;
import com.javaspring.javajrinterview.company.CompanyRepository;
import com.javaspring.javajrinterview.exceptions.customExceptions.ClientNotFoundException;
import com.javaspring.javajrinterview.exceptions.customExceptions.CompanyNotFoundException;
import com.javaspring.javajrinterview.exceptions.customExceptions.InsufficientBalanceException;
import com.javaspring.javajrinterview.tax.TaxService;
import com.javaspring.javajrinterview.transaction.dtos.TransactionDTO;
import com.javaspring.javajrinterview.transaction.dtos.TransactionDepositDTO;
import com.javaspring.javajrinterview.transaction.dtos.TransactionWithdrawDTO;
import com.javaspring.javajrinterview.webhook.EmailService;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private TaxService taxService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EmailService emailService;

    public Optional<TransactionEntity> findById(UUID id) {
        return transactionRepository.findById(id);
    }

    public TransactionDTO createTransaction(TransactionDTO transactionDTO) {
        CompanyEntity company = companyRepository.findById(transactionDTO.getCompanyId())
                .orElseThrow(() -> new CompanyNotFoundException("Empresa não encontrada."));
        ClientEntity client = clientRepository.findById(transactionDTO.getClientId())
                .orElseThrow(() -> new ClientNotFoundException("Cliente não encontrado."));
        
        double balance = calculateBalance(client.getId());
        
        if ("withdraw".equals(transactionDTO.getType())) {
            if (transactionDTO.getValue() > balance) {
                throw new InsufficientBalanceException("Saldo insuficiente para saque.");
            }
            
            double taxRate = 0.02; // Taxa de 2%
            double taxAmount = transactionDTO.getValue() * taxRate;
            double netValue = transactionDTO.getValue() - taxAmount; // Valor líquido após a taxa
            
            TransactionEntity transaction = new TransactionEntity(); // Inicializando a transação
            transaction.setClient(client);
            transaction.setCompany(company);
            transaction.setType(transactionDTO.getType());
            transaction.setValue(netValue);
            
            TransactionEntity savedTransaction = transactionRepository.save(transaction);
            
            taxService.createTax(savedTransaction, taxRate);
            
            TransactionWithdrawDTO responseDTO = modelMapper.map(savedTransaction, TransactionWithdrawDTO.class);
            responseDTO.setWithdraw(formatValueToCurrency(savedTransaction.getValue()));
            responseDTO.setTaxes(formatValueToCurrency(taxAmount));

            emailService.sendSimpleMessage(client.getEmail(), "Notificação de Transação", "Sua transação foi realizada com sucesso.");
            sendTransactionCallback("https://webhook.site/your-unique-url", transactionDTO);

            return responseDTO;
        } else if ("deposit".equals(transactionDTO.getType())) {
            TransactionEntity transaction = new TransactionEntity();
            transaction.setClient(client);
            transaction.setCompany(company);
            transaction.setType(transactionDTO.getType());
            transaction.setValue(transactionDTO.getValue());
            
            TransactionEntity savedTransaction = transactionRepository.save(transaction);
            
            TransactionDepositDTO responseDTO = modelMapper.map(savedTransaction, TransactionDepositDTO.class);
            responseDTO.setDeposit(formatValueToCurrency(savedTransaction.getValue()));

            emailService.sendSimpleMessage(client.getEmail(), "Notificação de Transação", "Sua transação foi realizada com sucesso.");
            sendTransactionCallback("https://webhook.site/your-unique-url", transactionDTO);
            
            return responseDTO;
        } else {
            throw new RuntimeException("Tipo de transação não suportado.");
        }
    }
    

    private double calculateBalance(UUID clientId) {
        double totalDeposits = calculateTotalAmountByType(clientId, "deposit");
        double totalWithdraws = calculateTotalAmountByType(clientId, "withdraw");
        return totalDeposits - totalWithdraws;
    }

    private double calculateTotalAmountByType(UUID clientId, String type) {
        return transactionRepository.findAllByClientIdAndType(clientId, type).stream()
                                    .mapToDouble(TransactionEntity::getValue)
                                    .sum();
    }

    private String formatValueToCurrency(double value) {
        Locale locale = Locale.forLanguageTag("pt-BR");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        return currencyFormatter.format(value / 100);
    }

    public void sendTransactionCallback(String companyCallbackUrl, TransactionDTO transaction) {
        try {
            restTemplate.postForObject(companyCallbackUrl, transaction, String.class);
        } catch (Exception e) {
            System.out.println("Falha ao enviar callback.");
        }
    }
}
