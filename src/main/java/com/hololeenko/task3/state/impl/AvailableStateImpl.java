package com.hololeenko.task3.state.impl;

import com.hololeenko.task3.entity.Customer;
import com.hololeenko.task3.entity.LibraryBook;
import com.hololeenko.task3.state.BookState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AvailableStateImpl implements BookState {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void borrow(LibraryBook book, Customer customer) {
        book.setState(new BorrowedStateImpl());
        LOGGER.info("Customer \"{}\" borrowed a book \"{}\"", customer.getName(), book.getBookName());
    }

    @Override
    public void returnBook(LibraryBook book, Customer customer){
        LOGGER.info("Book \"{}\" is available", book.getBookName());
    }

    @Override
    public boolean isAvailable() {
        return true;
    }
}
