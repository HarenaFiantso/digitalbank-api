package com.digital.bank.endpoint.rest.mapper;

import com.digital.bank.component.DebtComponent;
import com.digital.bank.model.Account;
import com.digital.bank.model.Debt;
import com.digital.bank.model.InterestRate;
import com.digital.bank.repository.AccountRepository;
import com.digital.bank.repository.InterestRateRepository;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public class DebtMapper {
  private final AccountRepository accountRepository;
  private final InterestRateRepository interestRateRepository;

  public DebtMapper(
      AccountRepository accountRepository, InterestRateRepository interestRateRepository) {
    this.accountRepository = accountRepository;
    this.interestRateRepository = interestRateRepository;
  }

  public DebtComponent toComponent(Debt debt) {
    try {
      Account account = this.accountRepository.getById(debt.getIdAccount());
      InterestRate interestRate = this.interestRateRepository.getById(debt.getIdInterestRate());
      return DebtComponent.builder()
              .idDebt(debt.getIdDebt())
              .amount(debt.getAmount())
              .debtDatetime(debt.getDebtDatetime())
              .account(account)
              .interestRate(interestRate)
              .build();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
