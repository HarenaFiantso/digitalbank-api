package com.digital.bank.endpoint.rest.controller;

import com.digital.bank.component.TransactionComponent;
import com.digital.bank.endpoint.rest.mapper.TransactionMapper;
import com.digital.bank.service.TransactionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService service;
    private final TransactionMapper mapper;

    public TransactionController(TransactionService service, TransactionMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("")
    public List<TransactionComponent> getAllTransactions(){
        return this.service.getAllTransactions()
                .stream()
                .map(this.mapper::toComponent)
                .collect(Collectors.toList());
    }
}
