package com.digital.bank.repository;

import com.digital.bank.model.TransactionCategory;
import com.digital.bank.util.drr.utility.DreamReflectRepository;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class TransactionCategoryRepository extends DreamReflectRepository<TransactionCategory> {
    public TransactionCategoryRepository(Connection connection) {
        super(connection);
    }

    @Override
    protected TransactionCategory mapResultSet(ResultSet resultSet) {
        try {
            return TransactionCategory.builder()
                    .idTransactionCategory(resultSet.getString("id_transaction_category"))
                    .name(resultSet.getString("name"))
                    .description(resultSet.getString("description"))
                    .build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
