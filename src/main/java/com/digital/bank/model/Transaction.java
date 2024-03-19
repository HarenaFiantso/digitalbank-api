package com.digital.bank.model;

import com.digital.bank.model.type.TransactionType;
import com.digital.bank.util.drr.annotation.Column;
import com.digital.bank.util.drr.annotation.CustomType;
import com.digital.bank.util.drr.annotation.Id;
import com.digital.bank.util.drr.annotation.Model;
import lombok.*;

import java.io.Serializable;

@Builder
@Getter
@Setter
@Model(table = "transaction")
public class Transaction implements Serializable {
    @Id
    @Column(name = "id_transaction")
    private final String idTransaction;
    @NonNull
    @Column(name = "transaction_type")
    @CustomType
    private final TransactionType transactionType;
    @NonNull
    @Column(name = "amount")
    private final Double amount;
    @NonNull
    @Column(name = "id_account")
    private final String idAccount;
    @NonNull
    @Column(name = "id_transfer")
    private final String idTransfer;
}
