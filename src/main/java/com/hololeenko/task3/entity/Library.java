package com.hololeenko.task3.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Library {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final Lock lock = new ReentrantLock();
    private final List<LibraryBook> booksInLibrary = new ArrayList<>();
    private final Map<Customer, List<LibraryBook>> customerWithBooks = new HashMap<>();
    private static Library library;


    private Library() {
    }

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



    public void addBook(LibraryBook book){
        if(!booksInLibrary.contains(book)){
            booksInLibrary.add(book);
            LOGGER.info("Book added to the library");
        }
    }

    public void getBook(Customer customer) {
        lock.lock();
        try{
            LOGGER.info("The beginning of book distribution for customer \"{}\"", customer.getName());

            if(!customerWithBooks.containsKey(customer)){
                customerWithBooks.put(customer, new ArrayList<>());
            }

            List<LibraryBook> customerBooks = customerWithBooks.get(customer);

            if(customerBooks.size() == 2){
                return;
            }

            boolean isBorrowed = false;

            for(LibraryBook book : booksInLibrary){
                if(book.isAvailable()){
                    book.borrowBook(customer);
                    customerBooks.add(book);
                    isBorrowed = true;
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        LOGGER.error(e);
                    }
                    break;
                }
            }

            if(!isBorrowed){
                LOGGER.info("Customer \"{}\" can't borrow books from library, because Library is empty", customer.getName());
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    LOGGER.error(e);
                }
            }


        }finally {
            lock.unlock();
        }

    }

}

