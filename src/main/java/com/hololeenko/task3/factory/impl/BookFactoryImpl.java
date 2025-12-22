package com.hololeenko.task3.factory.impl;

import com.hololeenko.task3.entity.LibraryBook;
import com.hololeenko.task3.factory.BookFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BookFactoryImpl implements BookFactory {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public LibraryBook createBook(String bookName, String author) {
        LOGGER.info("Create Book with name \"{}\" and author \"{}\"", bookName, author);
        return new LibraryBook(bookName, author);
    }
}
