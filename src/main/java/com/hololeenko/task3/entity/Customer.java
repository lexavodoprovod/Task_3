package com.hololeenko.task3.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;

public class Customer implements Callable<String> {

    private static final Logger LOGGER = LogManager.getLogger();

    private String name;
    private String email;

    public static final int BOOKS_LIMIT = 2;

    private final Library library = Library.getInstance();


    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @Override
    public String call(){
        LOGGER.info("The beginning of book distribution for customer \"{}\"", name);

        Map<Customer, List<LibraryBook>> customerWithBooks = library.getCustomerWithBooks();
        if(!customerWithBooks.containsKey(this)){
            customerWithBooks.put(this, new ArrayList<>());
        }

        List<LibraryBook> books = customerWithBooks.get(this);

        while(books.size() < BOOKS_LIMIT){
            library.getBooks(this);
        }

        return String.format("Customer \"%s\" successfully has been served", name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public boolean equals(Object o){
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Customer customer = (Customer) o;
        return this.name.equals(customer.name) && this.email.equals(customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Customer{");
        sb.append("name='").append(name).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
