package com.digital.bank.endpoint.rest.controller;

import com.digital.bank.model.TransactionCategory;
import com.digital.bank.service.TransactionCategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction-category")
public class TransactionCategoryController {

    private final TransactionCategoryService service;

    public TransactionCategoryController(TransactionCategoryService service) {
        this.service = service;
    }

    @GetMapping("")
    public List<TransactionCategory> getAllTransactionCategory(){
        return this.service.getAllTransactionCategory();
    }

    @PutMapping("")
    public List<TransactionCategory> createOrUpdateTransferCategory(
            @RequestBody List<TransactionCategory> toSave
    ){
        return this.service.createOrUpdateTransactionCategory(toSave);
    }

    @GetMapping("/{id}")
    public TransactionCategory getTransactionCategoryById(@PathVariable String id){
        return this.service.getTransactionCategoryById(id);
    }

    @DeleteMapping("/{id}")
    public TransactionCategory deleteTransactionCategoryById(@PathVariable String id){
        return this.service.deleteTransactionCategoryById(id);
    }
}

