package com.digital.bank.service;

import com.digital.bank.model.Debt;
import com.digital.bank.repository.DebtRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class DebtService {
  private final DebtRepository repository;

  public DebtService(DebtRepository repository) {
    this.repository = repository;
  }

  public List<Debt> getAllDebts() {
    try {
      return this.repository.findAll();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public List<Debt> createOrUpdateDebts(List<Debt> debts) {
    try {
      return this.repository.saveAll(debts);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public Debt getDebtById(String id) {
    try {
      return this.repository.getById(id);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public Debt deleteDebtById(String id) {
    try {
      return this.repository.delete(id);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
