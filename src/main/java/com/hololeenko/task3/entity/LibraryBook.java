package com.hololeenko.task3.entity;

import com.hololeenko.task3.state.impl.AvailableStateImpl;
import com.hololeenko.task3.state.BookState;

import java.util.Objects;

public class LibraryBook {

    private String bookName;
    private String bookAuthor;

    private BookState state = new AvailableStateImpl();

    public LibraryBook(String bookName, String bookAuthor) {
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
    }

    public void setState(BookState state) {
        this.state = state;
    }

    public void borrowBook(Customer customer) {
        state.borrow(this, customer);
    }

    public void returnBook(Customer customer) {
        state.returnBook(this, customer);
    }

    public boolean isAvailable(){
        return state.isAvailable();
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        LibraryBook book = (LibraryBook) o;
        return this.bookName.equals(book.bookName) && this.bookAuthor.equals(book.bookAuthor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookName, bookAuthor);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Book{");
        sb.append("bookName='").append(bookName).append('\'');
        sb.append(", bookAuthor='").append(bookAuthor).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
