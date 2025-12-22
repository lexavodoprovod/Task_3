package com.hololeenko.task3.reader;

import com.hololeenko.task3.exception.LibraryException;

import java.util.List;

public interface DataReader {
    List<String> read(String file) throws LibraryException;
}
