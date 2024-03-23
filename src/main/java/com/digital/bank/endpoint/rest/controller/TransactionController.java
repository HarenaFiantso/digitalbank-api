package com.digital.bank.endpoint.rest.controller;

import com.digital.bank.component.TransactionComponent;
import com.digital.bank.endpoint.rest.mapper.TransactionMapper;
import com.digital.bank.model.Transaction;
import com.digital.bank.service.TransactionService;
import org.springframework.web.bind.annotation.*;

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
    @PutMapping("")
    public List<TransactionComponent> createOrUpdateTransactions(
            @RequestBody List<Transaction> toSave
    ){
        return this.service.createOrUpdateTransactions(toSave)
                .stream()
                .map(this.mapper::toComponent)
                .collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    public TransactionComponent getTransactionById(@PathVariable String id){
        return this.mapper.toComponent(this.service.getTransactionById(id));
    }
    @DeleteMapping("/{id}")
    public TransactionComponent deleteTransactionById(@PathVariable String id){
        return this.mapper.toComponent(this.service.deleteTransactionById(id));
    }
}
