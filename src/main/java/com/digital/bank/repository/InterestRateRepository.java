package com.digital.bank.repository;

import com.digital.bank.model.InterestRate;
import com.digital.bank.util.drr.utility.DreamReflectRepository;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class InterestRateRepository extends DreamReflectRepository<InterestRate> {
  private final Connection connection;

  public InterestRateRepository(Connection connection) {
    super(connection);
    this.connection = connection;
  }

  public InterestRate getLatest() throws SQLException {
    String sql = "SELECT * FROM \"interest_rate\" ORDER BY interest_rate_datetime DESC LIMIT 1";
    PreparedStatement statement = this.connection.prepareStatement(sql);
    ResultSet resultSet = statement.executeQuery();
    if (!resultSet.next()) return null;
    return this.mapResultSet(resultSet);
  }

  public InterestRate getInitial() throws SQLException {
    String sql = "SELECT * FROM \"interest_rate\" ORDER BY interest_rate_datetime ASC LIMIT 1";
    PreparedStatement statement = this.connection.prepareStatement(sql);
    ResultSet resultSet = statement.executeQuery();
    if (!resultSet.next()) return null;
    return this.mapResultSet(resultSet);
  }

  @Override
  protected InterestRate mapResultSet(ResultSet resultSet) {
    try {
      return InterestRate.builder()
          .idInterestRate(resultSet.getString("id_interest_rate"))
          .value(resultSet.getDouble("value"))
          .interestRateDatetime(resultSet.getTimestamp("interest_rate_datetime").toInstant())
          .build();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
