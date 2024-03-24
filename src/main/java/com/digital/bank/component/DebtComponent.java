package com.digital.bank.component;

import com.digital.bank.model.Account;
import com.digital.bank.model.InterestRate;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Builder
@Getter
@Setter
public class DebtComponent {
  private final String idDebt;
  private final Double amount;
  private final Instant debtDatetime;
  private final Account account;
  private final InterestRate interestRate;
}
