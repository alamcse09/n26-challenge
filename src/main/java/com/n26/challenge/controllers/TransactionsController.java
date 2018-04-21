package com.n26.challenge.controllers;

import com.n26.challenge.models.Transaction;
import com.n26.challenge.models.error.TransactionValidationException;
import com.n26.challenge.services.TransactionValidationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/transactions")
@RestController
public class TransactionsController {

    private static final Logger LOGGER = LogManager.getLogger();
    private final TransactionValidationService transactionValidationService;

    @Autowired
    public TransactionsController(TransactionValidationService transactionValidationService) {
        this.transactionValidationService = transactionValidationService;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void registerTransaction(@RequestBody Transaction transaction) {
        transactionValidationService.validate(transaction);
        //TODO process transaction
    }

    @ExceptionHandler(TransactionValidationException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void handleException(TransactionValidationException ex) {
        LOGGER.error(ex);
    }
}