package com.digital.bank.model;

import com.digital.bank.util.drr.annotation.Column;
import com.digital.bank.util.drr.annotation.Id;
import com.digital.bank.util.drr.annotation.Model;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;

@Builder
@Getter
@Setter
@Model(table = "balance")
public class Balance implements Serializable {
    @Id
    @Column(name = "id_balance")
    private final String idBalance;
    @NonNull
    @Column(name = "amount")
    private final Double amount;
    @NonNull
    @Column(name = "balance_datetime")
    private final Instant balanceDatetime;
    @NonNull
    @Column(name = "id_account")
    private final String idAccount;
}
