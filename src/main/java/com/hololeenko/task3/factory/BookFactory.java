package com.hololeenko.task3.factory;

import com.hololeenko.task3.entity.LibraryBook;

public interface BookFactory {
    LibraryBook createBook(String bookName, String author);
}
