package com.hololeenko.task3.factory.impl;

import com.hololeenko.task3.entity.Customer;
import com.hololeenko.task3.factory.BookFactory;
import com.hololeenko.task3.factory.CustomerFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Callable;

public class CustomerFactoryImpl implements CustomerFactory {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Customer createCustomer(String name, String email) {
        LOGGER.info("Creating customer with name \"{}\" and email \"{}\"", name, email);
        return new  Customer(name, email);
    }
}
