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
@Model(table = "transfer")
public class Transfer {
    @Id
    @Column(name = "id_transfer")
    private final String idTransfer;
    @NonNull
    @Column(name = "id_transfer_group")
    private final String idTransferGroup;
}
