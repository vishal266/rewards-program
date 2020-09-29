package com.vishal.rewardscalculator.service;

import com.vishal.rewardscalculator.model.CustomerTransaction;

import java.util.List;

public interface TransactionService {
    CustomerTransaction create(CustomerTransaction customerTransaction);
    List<CustomerTransaction> listAll();
    List<CustomerTransaction> findByCustomerId(String customerId);
}
