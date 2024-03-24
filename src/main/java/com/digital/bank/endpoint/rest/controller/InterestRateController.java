package com.digital.bank.endpoint.rest.controller;

import com.digital.bank.model.InterestRate;
import com.digital.bank.service.InterestRateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/interest-rate")
public class InterestRateController {
  private final InterestRateService service;

  public InterestRateController(InterestRateService service) {
    this.service = service;
  }

  @GetMapping("")
  public List<InterestRate> getAllInterestRates() {
    return this.service.getAllInterestRates();
  }

  @PutMapping("")
  public List<InterestRate> createOrUpdateInterestRates(@RequestBody List<InterestRate> toSave) {
    return this.service.createOrUpdateInterestRates(toSave);
  }

  @GetMapping("/{id}")
  public InterestRate getInterestRateById(@PathVariable String id) {
    return this.service.getInterestRateById(id);
  }

  @DeleteMapping("/{id}")
  public InterestRate deleteInterestRateById(@PathVariable String id) {
    return this.service.deleteInterestRateById(id);
  }

  @GetMapping("/latest")
  public InterestRate getLatestInterestRate() {
    return this.service.getLatestInterestRate();
  }

  @GetMapping("/initial")
  public InterestRate getInitialInterestRate() {
    return this.service.getInitialInterestRate();
  }
}
