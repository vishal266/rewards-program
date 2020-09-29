package com.vishal.rewardscalculator.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.vishal.rewardscalculator.model.CustomerTransaction;

import java.util.List;

public interface TransactionRepo extends MongoRepository<CustomerTransaction, String> {
    List<CustomerTransaction> findByCustomerId(String customerId);
}
