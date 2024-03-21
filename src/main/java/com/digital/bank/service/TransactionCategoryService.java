package com.digital.bank.service;

import com.digital.bank.model.TransactionCategory;
import com.digital.bank.repository.TransactionCategoryRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class TransactionCategoryService {
    private final TransactionCategoryRepository repository;

    public TransactionCategoryService(TransactionCategoryRepository repository) {
        this.repository = repository;
    }

    public List<TransactionCategory> getAllTransactionCategory(){
        try{
            return this.repository.findAll();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<TransactionCategory> createOrUpdateTransactionCategory(
            List<TransactionCategory> transactionCategories
    ){
        try {
            return this.repository.saveAll(transactionCategories);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public TransactionCategory getTransactionCategoryById(String id){
        try {
            return this.repository.getById(id);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public TransactionCategory deleteTransactionCategoryById(String id){
        try {
            return this.repository.delete(id);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
