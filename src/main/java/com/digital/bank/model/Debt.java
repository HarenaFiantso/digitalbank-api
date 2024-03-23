package com.digital.bank.model;

import com.digital.bank.util.drr.annotation.Column;
import com.digital.bank.util.drr.annotation.Id;
import com.digital.bank.util.drr.annotation.Model;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.Instant;

@Builder
@Getter
@Setter
@Model(table = "debt")
public class Debt {
  @Id
  @Column(name = "id_debt")
  private final String idDebt;

  @NonNull
  @Column(name = "amount")
  private final Double amount;

  @NonNull
  @Column(name = "debt_datetime")
  private final Instant debtDatetime;

  @NonNull
  @Column(name = "id_account")
  private final String idAccount;

  @NonNull
  @Column(name = "id_interest_rate")
  private final String idInterestRate;
}
