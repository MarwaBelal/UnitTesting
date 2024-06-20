package org.example.store;

import org.example.account.AccountManager;
import org.example.account.AccountManagerImpl;
import org.example.account.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

public class StoreMockitoTest {

    Store store;

    Product product = new Product();
    Customer customer = new Customer();
    AccountManager accountManager = new AccountManagerImpl();

    @Test
    void testBuyIfQuantityIsValid() {
        // Arrange
        product.setQuantity(8);
        AccountManager accountManager = Mockito.mock(AccountManager.class);
        when(accountManager.withdraw(any(), anyInt())).thenReturn("success");
        store = new StoreImpl(accountManager);

        // Act
        store.buy(product, customer);

        // Assert
        Assertions.assertEquals(7, product.getQuantity());
    }

    @Test
    void testBuyIfQuantityIsInvalid() {
        try {
            // Arrange
            product.setQuantity(0);
            AccountManager accountManager = Mockito.mock(AccountManager.class);
            when(accountManager.withdraw(any(), anyInt())).thenReturn("success");
            store = new StoreImpl(accountManager);

            // Act
            store.buy(product, customer);
        } catch (final Exception ex) {

            // Assert
            Assertions.assertEquals("Product out of stock", ex.getMessage());
        }
    }

    @Test
    void testWithdrawWhenCustomerCreditIsNotAllowed() {
        // Arrange
        customer.setBalance(10);
        customer.setCreditAllowed(false);

        // Act
        String resultMsg =  accountManager.withdraw(customer, 20);
        
        // Assert
        Assertions.assertEquals("insufficient account balance", resultMsg);
    }
}