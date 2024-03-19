package com.digital.bank.service;

import com.digital.bank.model.Account;
import com.digital.bank.model.Balance;
import com.digital.bank.repository.AccountRepository;
import com.digital.bank.repository.BalanceRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.Instant;
import java.util.List;

@Service
public class AccountService {
    private final AccountRepository repository;
    private final BalanceRepository balanceRepository;
    public AccountService(AccountRepository repository, BalanceRepository balanceRepository) {
        this.repository = repository;
        this.balanceRepository = balanceRepository;
    }

    public List<Account> getAllAccounts(){
        try {
            return this.repository.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Account> createOrUpdateAccounts(List<Account> accounts){
        try {
            for (Account account : accounts) {
                if(account.getIdAccount() != null)
                    balanceRepository.save(
                            Balance.builder()
                                    .amount(0.0)
                                    .balanceDatetime(Instant.now())
                                    .idAccount(account.getIdAccount())
                                    .build()
                    );
            }

            return this.repository.saveAll(accounts);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Account getAccountById(String id){
        try {
            return this.repository.getById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Account deleteAccountById(String id){
        try {
            return this.repository.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
