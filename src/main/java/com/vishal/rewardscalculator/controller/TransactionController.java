package com.vishal.rewardscalculator.controller;

import com.vishal.rewardscalculator.exception.RewardsException;
import com.vishal.rewardscalculator.model.CustomerTransaction;
import com.vishal.rewardscalculator.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
@CrossOrigin(origins = "*")
public class TransactionController {
    private final Logger log = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    private TransactionService tsvc;

    @GetMapping()
    public List<CustomerTransaction> getAllTransactions() {
        log.info("Retrieve all transactions for all customers");
        return tsvc.listAll();
    }

    @GetMapping(params="customerId")
    public List<CustomerTransaction> getAllTransactionsForACustomer(@RequestParam("customerId") String customerId) throws RewardsException {
        log.info("Retrieve all transactions for a customer: " + customerId);
        if(customerId == null || customerId.isEmpty()) {
            throw new RewardsException("Customer id cannot be null!");
        }
        List<CustomerTransaction> res = tsvc.findByCustomerId(customerId);
        if(res == null) {
            throw new RewardsException("No transaction record found for customer id: " + customerId);
        }
        return res;
    }

    @PostMapping
    public CustomerTransaction createTransaction(@Valid @RequestBody CustomerTransaction customerTransaction) throws RewardsException {
        log.info("Calculate reward points for customer: " + customerTransaction.getCustomerId());
        if(customerTransaction == null) {
            throw new RewardsException("Invalid customer transaction.");
        }
        if(customerTransaction.getId() != null) {
            throw new RewardsException("To create a new transaction record, ID has to be blank");
        }
        if(customerTransaction.getCustomerId() == null || customerTransaction.getAmount() == null) {
            throw new RewardsException("To create a new transaction record, customer ID and transaction amount cannot be blank");
        }
        if(customerTransaction.getAmount() < 0) {
            throw new RewardsException("Transaction amount cannot be in negative!");
        }
        return tsvc.create(customerTransaction);
    }
}
