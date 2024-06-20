package org.example.store;

import org.example.account.AccountManager;
import org.example.account.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StoreTest {

    Store store;

    Product product = new Product();
    Customer customer = new Customer();

    static class AlwaysReturnSuccessAccountManager implements AccountManager {

        @Override
        public void deposit(Customer customer, int amount) {

        }

        @Override
        public String withdraw(Customer customer, int amount) {
            return "success";
        }
    }


    @Test void test1() {
        // Arrange
        product.setQuantity(8);
        store = new StoreImpl(new AlwaysReturnSuccessAccountManager());

        // Act
        store.buy(product, customer);

        // Assert
        Assertions.assertEquals(7, product.getQuantity());
    }

}
