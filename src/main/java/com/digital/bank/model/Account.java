package com.digital.bank.model;

import com.digital.bank.util.drr.annotation.Column;
import com.digital.bank.util.drr.annotation.Id;
import com.digital.bank.util.drr.annotation.Model;
import lombok.*;

import java.io.Serializable;
import java.sql.Date;

@Builder
@Getter
@Setter
@Model(table = "account")
@ToString
public class Account implements Serializable {
    @Id
    @Column(name = "id_account")
    private final String idAccount;
    @NonNull
    @Column(name = "first_name")
    private final String firstName;
    @NonNull
    @Column(name = "last_name")
    private final String lastName;
    @NonNull
    @Column(name = "birth_date")
    private final Date birthDate;
    @NonNull
    @Column(name = "monthly_salary")
    private final Double monthlySalary;
    @NonNull
    @Column(name = "over_drafted")
    private final Boolean overDrafted;
}
