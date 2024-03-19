package com.digital.bank.repository;

import com.digital.bank.model.Account;
import com.digital.bank.util.drr.utility.DreamReflectRepository;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class AccountRepository extends DreamReflectRepository<Account> {
    public AccountRepository(Connection connection) {
        super(connection);
    }
    @Override
    protected Account mapResultSet(ResultSet resultSet) {
        try {
            return Account.builder()
                    .idAccount(resultSet.getString("id_account"))
                    .firstName(resultSet.getString("first_name"))
                    .lastName(resultSet.getString("last_name"))
                    .birthDate(resultSet.getDate("birth_date"))
                    .monthlySalary(resultSet.getDouble("monthly_salary"))
                    .overDrafted(resultSet.getBoolean("over_drafted"))
                    .build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
