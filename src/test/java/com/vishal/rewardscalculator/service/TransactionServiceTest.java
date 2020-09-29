package com.vishal.rewardscalculator.service;

import com.vishal.rewardscalculator.CustomerTransactionTestHelpers;
import com.vishal.rewardscalculator.Repository.TransactionRepo;
import com.vishal.rewardscalculator.model.CustomerTransaction;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Service unit tests for the customerTransactionService. This is not tightly coupled to spring and primarily uses only mocks.
 */
public class TransactionServiceTest {

    @Mock
    private TransactionRepo transactionRepo;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllCustomerTransactions(){
        List<CustomerTransaction> customerTransactionsList = CustomerTransactionTestHelpers.createSampleCustomerTransaction();
        when(transactionRepo.findAll()).thenReturn(customerTransactionsList);

        List<CustomerTransaction> result = transactionService.listAll();
        assertNotNull(result);
        assertEquals(false, result.isEmpty());
        assertEquals(5, result.size());
    }

    @Test
    public void testGetTransactionsByCustomerId(){
        List<CustomerTransaction> customerTransactionsList = CustomerTransactionTestHelpers.createSampleCustomerTransaction();
        when(transactionRepo.findByCustomerId("1-C")).thenReturn(customerTransactionsList);

        List<CustomerTransaction> result = transactionService.findByCustomerId("1-C");
        assertEquals(5, result.size());
    }

    @Test
    public void testGetInvalidTransactionByCustomerId(){
        List<CustomerTransaction> customerTransactionsList = CustomerTransactionTestHelpers.createSampleCustomerTransaction();
        when(transactionRepo.findByCustomerId("1-C")).thenReturn(customerTransactionsList);

        List<CustomerTransaction> result = transactionService.findByCustomerId("asdasd");
        assertTrue(result.isEmpty());
    }

    @Test
    public void testCreateCustomerTransaction(){
        CustomerTransaction customerTransaction = CustomerTransactionTestHelpers.createASampleCustomerTransaction();
        when(transactionService.create(customerTransaction)).thenReturn(customerTransaction);

        CustomerTransaction result = transactionService.create(customerTransaction);
        assertEquals("1-C", result.getCustomerId());
    }
}
