package com.digital.bank.component;

import com.digital.bank.model.Account;
import com.digital.bank.model.TransactionCategory;
import com.digital.bank.model.Transfer;
import com.digital.bank.model.type.TransactionType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Builder
@Getter
@Setter
public class TransactionComponent {
    private final String idTransaction;
    private final TransactionType transactionType;
    private final Instant transactionDatetime;
    private final Double amount;
    private final String reason;
    private final Account account;
    private final Transfer transfer;
    private final TransactionCategory category;
}
