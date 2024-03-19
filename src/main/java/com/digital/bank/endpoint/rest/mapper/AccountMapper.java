package com.digital.bank.endpoint.rest.mapper;

import com.digital.bank.component.AccountComponent;
import com.digital.bank.model.Account;
import com.digital.bank.model.Balance;
import com.digital.bank.model.Transaction;
import com.digital.bank.repository.BalanceRepository;
import com.digital.bank.repository.TransactionRepository;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component
public class AccountMapper {

    private final TransactionRepository transactionRepository;
    private final BalanceRepository balanceRepository;

    public AccountMapper(TransactionRepository transactionRepository, BalanceRepository balanceRepository) {
        this.transactionRepository = transactionRepository;
        this.balanceRepository = balanceRepository;
    }

    public AccountComponent toComponent(Account account){

        try {
            List<Transaction> lastTransactions = this.transactionRepository.getLastTransactionOfAccount(account.getIdAccount());
            Balance currentBalance = this.balanceRepository.getCurrentBalanceOfAccount(account.getIdAccount());

            return AccountComponent.builder()
                    .idAccount(account.getIdAccount())
                    .firstName(account.getFirstName())
                    .lastName(account.getLastName())
                    .birthDate(account.getBirthDate())
                    .monthlySalary(account.getMonthlySalary())
                    .overDrafted(account.getOverDrafted())
                    .transactions(lastTransactions)
                    .balance(currentBalance)
                    .build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
