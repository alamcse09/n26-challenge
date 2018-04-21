package com.n26.challenge.controllers;

import com.n26.challenge.models.Transaction;
import com.n26.challenge.services.TransactionValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/transactions")
@RestController
public class TransactionsController {

    private final TransactionValidationService transactionValidationService;

    @Autowired
    public TransactionsController(TransactionValidationService transactionValidationService) {
        this.transactionValidationService = transactionValidationService;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void registerTransaction(@RequestBody Transaction transaction) {
        if (!transactionValidationService.isValid(transaction)) {
            //TODO
        }
        //TODO
    }
}