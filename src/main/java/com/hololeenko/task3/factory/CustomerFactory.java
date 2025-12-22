package com.hololeenko.task3.factory;

import com.hololeenko.task3.entity.Customer;

public interface CustomerFactory {
    Customer createCustomer(String name, String email);
}
