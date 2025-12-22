package com.hololeenko.task3.state;

import com.hololeenko.task3.entity.Customer;
import com.hololeenko.task3.entity.Library;
import com.hololeenko.task3.entity.LibraryBook;

public interface BookState {
    void borrow(LibraryBook book, Customer customer);
    void returnBook(LibraryBook book, Customer customer);
    boolean isAvailable();
}