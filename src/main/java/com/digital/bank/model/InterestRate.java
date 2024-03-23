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
@Model(table = "interest_rate")
public class InterestRate {
  @Id
  @Column(name = "id_interest_rate")
  private final String idInterestRate;

  @NonNull
  @Column(name = "value")
  private final Double value;

  @NonNull
  @Column(name = "interest_rate_datetime")
  private final Instant interestRateDatetime;
}
