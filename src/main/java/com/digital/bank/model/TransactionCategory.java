package com.digital.bank.model;

import com.digital.bank.util.drr.annotation.Column;
import com.digital.bank.util.drr.annotation.Id;
import com.digital.bank.util.drr.annotation.Model;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Builder
@Getter
@Setter
@Model(table = "transaction_category")
public class TransactionCategory {
    @Id
    @Column(name = "id_transaction_category")
    private final String idTransactionCategory;
    @NonNull
    @Column(name = "name")
    private final String name;
    @NonNull
    @Column(name = "description")
    private final String description;
}
