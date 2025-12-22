package com.hololeenko.task3.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Library {

    private static final Logger LOGGER = LogManager.getLogger();

    private static Library library;
    private static final Lock lock = new ReentrantLock();
    private final List<LibraryBook> booksInLibrary = new ArrayList<>();
    private final Map<Customer, List<LibraryBook>> customerWithBooks = new HashMap<>();
    private static final int CAPACITY = 15;


    public static Library getInstance(){
        if(library == null){
            lock.lock();
            try{
                if(library == null){
                    library = new Library();
                    LOGGER.info("Library is created");
                }
            }finally {
                lock.unlock();
            }
        }
        return library;
    }

    private Library() {
    }

    public void addBook(LibraryBook book){
        if(!booksInLibrary.contains(book) && booksInLibrary.size() <= CAPACITY){
            booksInLibrary.add(book);
            LOGGER.info("Book added to the library");
        }
    }

    public void getBooks(Customer customer) {
        lock.lock();
        try{

            List<LibraryBook> concurrentBooks = customerWithBooks.get(customer);

            for(LibraryBook book : booksInLibrary){
                if(book.isAvailable()){
                    book.borrowBook(customer);
                    concurrentBooks.add(book);
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        LOGGER.error(e);
                    }
                    break;
                }
            }
        }finally {
            lock.unlock();
        }

    }

    public List<LibraryBook> getBooksInLibrary() {
        lock.lock();
        try {
            return new ArrayList<>(booksInLibrary);
        }finally {
            lock.unlock();
        }
    }

    public Map<Customer, List<LibraryBook>> getCustomerWithBooks() {
        lock.lock();
        try {
            return new HashMap<>(customerWithBooks);
        }finally {
            lock.unlock();
        }
    }
}

