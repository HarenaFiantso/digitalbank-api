package com.digital.bank.repository;

import com.digital.bank.model.Debt;
import com.digital.bank.util.drr.utility.DreamReflectRepository;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class DebtRepository extends DreamReflectRepository<Debt> {
  public DebtRepository(Connection connection) {
    super(connection);
  }

  @Override
  protected Debt mapResultSet(ResultSet resultSet) {
    try{
      return Debt.builder()
              .idDebt(resultSet.getString("id_debt"))
              .amount(resultSet.getDouble("amount"))
              .debtDatetime(resultSet.getTimestamp("debt_datetime").toInstant())
              .idAccount(resultSet.getString("id_account"))
              .idInterestRate(resultSet.getString("id_interest_rate"))
              .build();
    }catch (SQLException e){
      throw new RuntimeException(e);
    }
  }
}
