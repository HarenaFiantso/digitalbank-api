package com.digital.bank.service;

import com.digital.bank.model.InterestRate;
import com.digital.bank.repository.InterestRateRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class InterestRateService {
  private final InterestRateRepository repository;

  public InterestRateService(InterestRateRepository repository) {
    this.repository = repository;
  }

  public List<InterestRate> getAllInterestRates() {
    try {
      return this.repository.findAll();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public List<InterestRate> createOrUpdateInterestRates(List<InterestRate> interestRates) {
    try {
      return this.repository.saveAll(interestRates);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public InterestRate getInterestRateById(String id) {
    try {
      return this.repository.getById(id);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public InterestRate deleteInterestRateById(String id) {
    try {
      return this.repository.delete(id);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public InterestRate getLatestInterestRate() {
    try {
      return this.repository.getLatest();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public InterestRate getInitialInterestRate() {
    try{
      return this.repository.getInitial();
    }catch (SQLException e){
      throw new RuntimeException(e);
    }
  }
}
