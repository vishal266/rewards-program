package com.vishal.rewardscalculator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vishal.rewardscalculator.model.CustomerTransaction;


import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for unit tests
 */
public class CustomerTransactionTestHelpers {
    private static Gson gson = new GsonBuilder().setDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz").create();

    /**
     * Set properties for customerTransaction
     * @param customerTransaction
     */
    public static void setPropsForCustomerTransaction(CustomerTransaction customerTransaction) {
        customerTransaction.setCustomerId("1-C");
        customerTransaction.setAmount((double) 120);
    }

    /**
     * Set invalid properties for customerTransaction
     * @param customerTransaction
     */
    public static void setInvalidPropsForCustomerTransaction(CustomerTransaction customerTransaction) {
        customerTransaction.setCustomerId(null);
    }

    /**
     * convert customerTransaction to json using gson
     * @param customerTransaction
     * @return
     */
    public static String customerTransactionToJSON(CustomerTransaction customerTransaction) {
        return gson.toJson(customerTransaction);
    }

    /**
     * create a sample customerTransaction
     * @return
     */
    public static CustomerTransaction createASampleCustomerTransaction() {
        CustomerTransaction customerTransaction = new CustomerTransaction();
        customerTransaction.setId("1-T");
        customerTransaction.setCustomerId("1-C");
        customerTransaction.setAmount((double) 120);
        customerTransaction.setRewardPoints((double) 90);

        return customerTransaction;
    }

    /**
     * create a list of customerTransactions.
     * @return
     */
    public static List<CustomerTransaction> createSampleCustomerTransaction() {
        List<CustomerTransaction> customerTransactionsList = new ArrayList<>();
        for(int i=1; i<=5; i++) {
            CustomerTransaction customerTransaction = new CustomerTransaction();
            customerTransaction.setId(i + "-T");
            customerTransaction.setCustomerId("1-C");
            customerTransaction.setAmount((double) 120);
            customerTransaction.setRewardPoints((double) 90);
            customerTransactionsList.add(customerTransaction);
        }
        return customerTransactionsList;
    }

    public static CustomerTransaction JSONtoCustomerTransaction(String json) {
        return gson.fromJson(json, CustomerTransaction.class);
    }
}
