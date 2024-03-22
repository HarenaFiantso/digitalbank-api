package com.digital.bank.endpoint.rest.mapper;

import com.digital.bank.component.TransactionComponent;
import com.digital.bank.model.Transaction;
import com.digital.bank.repository.AccountRepository;
import com.digital.bank.repository.TransactionRepository;
import com.digital.bank.repository.TransferRepository;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public class TransactionMapper {

    private final TransferRepository transferRepository;
    private final AccountRepository accountRepository;

    public TransactionMapper(TransferRepository transferRepository, AccountRepository accountRepository) {
        this.transferRepository = transferRepository;
        this.accountRepository = accountRepository;
    }

    public TransactionComponent toComponent(Transaction transaction){
        try {
            return TransactionComponent.builder()
                    .idTransaction(transaction.getIdTransaction())
                    .transactionType(transaction.getTransactionType())
                    .transactionDatetime(transaction.getTransactionDatetime())
                    .reason(transaction.getReason())
                    .amount(transaction.getAmount())
                    .account(accountRepository.getById(transaction.getIdAccount()))
                    .transfer(
                            transaction.getIdTransfer() == null ? null : transferRepository.getById(transaction.getIdTransfer())
                    )
                    .build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
