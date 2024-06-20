package org.example.account;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AccountManagerTest {

    Customer customer = new Customer();
    AccountManager accountManager = new AccountManagerImpl();

    @Test
    void givenAmountBelowMaxCreditForNormalCustomerWhenWithdrawThenSubtractFromBalance() {
        // Arrange
        customer.setBalance(100);

        // Act
        String result = accountManager.withdraw(customer, 80);

        // Assert
        int expectedBalance = customer.getBalance();
        Assertions.assertEquals(20, expectedBalance);
        Assertions.assertEquals("success", result);
    }

    @Test
    void givenAmountHigherMaxCreditForNormalCustomerWhenWithdrawThenReturnMaximumCreditExceed() {
        // Arrange
        customer.setBalance(1500);

        // Act
        String result = accountManager.withdraw(customer, 80);

        // Assert
        Assertions.assertEquals("maximum credit exceeded", result);
    }

    @Test
    void givenAmountHigherMaxCreditForVIPCustomerWhenWithdrawThenSubtractFromBalance() {
        // Arrange
        customer.setBalance(1500);
        customer.setVip(true);
        
        // Act
        String result = accountManager.withdraw(customer, 80);

        // Assert
        Assertions.assertEquals("success", result);
    }

}
