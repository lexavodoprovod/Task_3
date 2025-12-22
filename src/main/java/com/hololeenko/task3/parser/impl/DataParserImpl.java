package com.hololeenko.task3.parser.impl;

import com.hololeenko.task3.parser.DataParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class DataParserImpl implements DataParser {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final String LINE_REGEX = "-";
    @Override
    public String[] parse(String line) {
        LOGGER.info("Splitting line data \"{}\"", line);
        String[] data = line.split(LINE_REGEX);
        return data;
    }
}
