package com.hololeenko.task3.application;

import com.hololeenko.task3.entity.Customer;
import com.hololeenko.task3.entity.Library;
import com.hololeenko.task3.entity.LibraryBook;
import com.hololeenko.task3.exception.LibraryException;
import com.hololeenko.task3.factory.BookFactory;
import com.hololeenko.task3.factory.CustomerFactory;
import com.hololeenko.task3.factory.impl.BookFactoryImpl;
import com.hololeenko.task3.factory.impl.CustomerFactoryImpl;
import com.hololeenko.task3.parser.DataParser;
import com.hololeenko.task3.parser.impl.DataParserImpl;
import com.hololeenko.task3.reader.DataReader;
import com.hololeenko.task3.reader.impl.DataReaderImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.*;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {

        DataReader reader = new DataReaderImpl();
        DataParser parser = new DataParserImpl();

        Library library = Library.getInstance();


        List<String> bookLines = List.of();
        List<String> customerLines = List.of();
        try {
            bookLines = reader.read("data/books.txt");
            customerLines = reader.read("data/customers.txt");
        } catch (LibraryException e) {
            LOGGER.error(e.getMessage());
        }

        BookFactory bookFactory = new BookFactoryImpl();
        for(String bookLine: bookLines){
            String[] parts = parser.parse(bookLine);
            String bookName = parts[0];
            String bookEmail = parts[1];
            LibraryBook book = bookFactory.createBook(bookName, bookEmail);
            library.addBook(book);
        }

        CustomerFactory  customerFactory = new CustomerFactoryImpl();
        Customer[] customers = new Customer[3];
        int i = 0;
        for(String customerLine: customerLines){
            String[] parts = parser.parse(customerLine);
            String customerName = parts[0];
            String customerEmail = parts[1];
            Customer customer = customerFactory.createCustomer(customerName, customerEmail);
            customers[i] = customer;
            i++;
        }



        ExecutorService executor = Executors.newFixedThreadPool(3);

        Customer firstCustomer = customers[0];
        Customer secondCustomer = customers[1];
        Customer thirdCustomer = customers[2];

        try {
            Future<String> customer1Future = executor.submit(firstCustomer);
            String success1 = customer1Future.get();
            LOGGER.info(success1);

            Future<String> customer2Future = executor.submit(secondCustomer);
            String success2 = customer2Future.get();
            LOGGER.info(success2);

            Future<String> customer3Future = executor.submit(thirdCustomer);
            String success3 = customer3Future.get();
            LOGGER.info(success3);

            LOGGER.info("My map {}", library.getCustomerWithBooks());

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        } finally {
            executor.shutdown();
        }



    }
}
