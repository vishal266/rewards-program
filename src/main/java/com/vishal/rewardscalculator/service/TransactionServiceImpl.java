package com.vishal.rewardscalculator.service;

import com.vishal.rewardscalculator.Repository.TransactionRepo;
import com.vishal.rewardscalculator.model.CustomerTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.vishal.rewardscalculator.utils.TransactionUtils.VAL_FIFTY;
import static com.vishal.rewardscalculator.utils.TransactionUtils.VAL_HUNDRED;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final Logger log = LoggerFactory.getLogger(TransactionServiceImpl.class);
    private final TransactionRepo repo;

    @Autowired
    public TransactionServiceImpl(TransactionRepo repo) {
        this.repo = repo;
    }

    @Override
    public List<CustomerTransaction> listAll() {
        log.info("Listing all transactions...");
        return repo.findAll();
    }

    @Override
    public CustomerTransaction create(CustomerTransaction customerTransaction) {
        log.info("Calculating reward points...");
        Double pointsOverfifty  = 0.0, pointsOverHundred = 0.0;
        Double totalRewardPoints = 0.0 ;
        Double amount = customerTransaction.getAmount();
        if(amount > VAL_HUNDRED) {
            pointsOverHundred =  2 * (amount - VAL_HUNDRED);
            pointsOverfifty = (amount - VAL_FIFTY) - (amount - VAL_HUNDRED);
        } else if(amount > VAL_FIFTY) {
            pointsOverfifty = amount - VAL_FIFTY;
        }
        totalRewardPoints = pointsOverHundred + pointsOverfifty;

        customerTransaction.setRewardPoints(totalRewardPoints);

        return repo.save(customerTransaction);
    }

    @Override
    public List<CustomerTransaction> findByCustomerId(String customerId) {
        log.info("List transactions for customer Id: " + customerId);
        return repo.findByCustomerId(customerId);
    }
}
