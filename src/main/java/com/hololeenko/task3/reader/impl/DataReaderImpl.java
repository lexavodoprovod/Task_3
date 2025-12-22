package com.hololeenko.task3.reader.impl;

import com.hololeenko.task3.exception.LibraryException;
import com.hololeenko.task3.reader.DataReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DataReaderImpl implements DataReader {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public List<String> read(String file) throws LibraryException {

        Path path = Paths.get(file);
        List<String> data;

        try {
            data = Files.readAllLines(path);
            LOGGER.info("Successfully read file");
        } catch (NoSuchFileException e) {
            throw new LibraryException("File does not exist", e);
        }catch (IOException e) {
            throw new LibraryException("Error reading file", e);
        }

        return data;
    }
}
