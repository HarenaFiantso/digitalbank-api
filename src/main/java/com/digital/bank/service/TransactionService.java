package com.digital.bank.service;

import com.digital.bank.model.Transaction;
import com.digital.bank.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    public List<Transaction> getAllTransactions(){
        try {
            return this.repository.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
