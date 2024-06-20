package org.example.account;

public class AccountManagerImpl implements AccountManager {
    private static final int MAX_CREDIT = 1000;

    @Override
    public void deposit(Customer customer, int amount) {
        customer.setBalance(customer.getBalance() + amount);
    }

    @Override
    public String withdraw(Customer customer, int amount) {
        int expectedBalance = customer.getBalance() - amount;
        if (expectedBalance < 0) {
            if (!customer.isCreditAllowed()) {
                return "insufficient account balance";
            }
            // It'll never enter this else if case
            // } else if (expectedBalance > MAX_CREDIT && !customer.isVip()) {
            // return "maximum credit exceeded";
            // }
        } else if (expectedBalance > MAX_CREDIT && !customer.isVip()) {
            return "maximum credit exceeded";
        }
        customer.setBalance(expectedBalance);
        return "success";
    }
}
