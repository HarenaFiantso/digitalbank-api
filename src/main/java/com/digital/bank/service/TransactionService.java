package com.digital.bank.service;

import com.digital.bank.model.Balance;
import com.digital.bank.model.Transaction;
import com.digital.bank.model.type.TransactionType;
import com.digital.bank.repository.BalanceRepository;
import com.digital.bank.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository repository;
    private final BalanceRepository balanceRepository;

    public TransactionService(TransactionRepository repository, BalanceRepository balanceRepository) {
        this.repository = repository;
        this.balanceRepository = balanceRepository;
    }

    public List<Transaction> getAllTransactions(){
        try {
            return this.repository.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Transaction> createOrUpdateTransactions(List<Transaction> transactions){
        try{
            List<Transaction> result = new ArrayList<>();

            for (Transaction transaction : transactions) {
                if(transaction.getTransactionType() == TransactionType.EXPENSE){
                    Balance currentAccountBalance = this.balanceRepository
                            .getCurrentBalanceOfAccount(transaction.getIdAccount());

                    if (currentAccountBalance.getAmount() < transaction.getAmount())
                       throw new RuntimeException("Your balance is insufficient for this transaction");
                }

                Transaction saved = this.applyTransactionToAccount(transaction);
                result.add(saved);
            }
            return result;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Transaction getTransactionById(String id){
        try{
            return this.repository.getById(id);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Transaction deleteTransactionById(String id){
        try{
            return this.repository.delete(id);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    private Transaction applyTransactionToAccount(Transaction transaction) throws SQLException{
        Transaction saved = this.repository.save(transaction);
        Double currentBalanceAmount = this.balanceRepository
                .getCurrentBalanceOfAccount(transaction.getIdAccount())
                .getAmount();
        Double updatedBalance = transaction.getTransactionType() == TransactionType.INCOME
                ? currentBalanceAmount + transaction.getAmount()
                : currentBalanceAmount - transaction.getAmount();
        Balance savedBalance = this.balanceRepository.save(
                Balance.builder()
                        .idAccount(transaction.getIdAccount())
                        .amount(updatedBalance)
                        .balanceDatetime(Instant.now())
                        .build()
        );

        return saved;
    }
}
