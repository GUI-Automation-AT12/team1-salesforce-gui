package org.fundacionjala.salesforce.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * [SL] This class read a csv file.
 */
public final class CSVReader {

    /**
     * [SL] Constructor.
     */
    private CSVReader() {

    }

    /**
     * [SL] Gets all data of a csv file.
     *
     * @param filePath
     * @return a List of arrays with all data of the csv
     * @throws IOException
     */
    public static List<CSVRecord> getRecords(final String filePath) throws IOException {
        InputStream inputStream = new FileInputStream(filePath);
        InputStreamReader input = new InputStreamReader(inputStream);
        CSVParser csvParser = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(input);
        return csvParser.getRecords();
    }
}
