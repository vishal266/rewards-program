package com.vishal.rewardscalculator.controller;

import com.vishal.rewardscalculator.CustomerTransactionTestHelpers;
import com.vishal.rewardscalculator.RewardsCalculatorApplication;
import com.vishal.rewardscalculator.model.CustomerTransaction;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Unit tests for controller - this just performs a simple rest API call using mocks to the controller and checks if
 * the HTTP response is valid for the corresponding call
 */
@WebAppConfiguration
@ContextConfiguration(classes = RewardsCalculatorApplication.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransactionControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private TransactionController transactionController;

    private String baseUrl = "/api/v1/transactions";

    private CustomerTransaction customerTransaction;
    private String json;

    private CustomerTransaction invalidCustomerTransaction;
    private String invalidJson;

    @Before
    public void setup() {
        this.transactionController = mock(TransactionController.class);
        this.mockMvc = MockMvcBuilders.standaloneSetup(transactionController).build();
        customerTransaction = new CustomerTransaction();
        CustomerTransactionTestHelpers.setPropsForCustomerTransaction(customerTransaction);
        json = CustomerTransactionTestHelpers.customerTransactionToJSON(customerTransaction);

        invalidCustomerTransaction = new CustomerTransaction();
        CustomerTransactionTestHelpers.setPropsForCustomerTransaction(invalidCustomerTransaction);
        invalidCustomerTransaction.setCustomerId("");
        invalidJson = CustomerTransactionTestHelpers.customerTransactionToJSON(invalidCustomerTransaction);
    }

    @Test
    public void createCustomerTransaction() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post(baseUrl).contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void getCustomerTransactionForCustomerId() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get(baseUrl).param("customerId", "1-C").contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void getInvalidCustomerTransactionForCustomerId() throws Exception {
        String s = null;
        this.mockMvc.perform(MockMvcRequestBuilders.get(baseUrl).param("customerId", s).contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void getAllCustomerTransactions() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get(baseUrl).contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
}
