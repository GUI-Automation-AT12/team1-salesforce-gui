package org.fundacionjala.salesforce.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * [SL] This class read a csv file.
 */
public final class CSVUtil {

    /**
     * [SL] Constructor.
     */
    private CSVUtil() {

    }

    /**
     * [SL] Gets all data of a csv file.
     *
     * @param filePath
     * @return a List of arrays with all data of the csv
     * @throws IOException
     */
    public static List<String[]> readCSVFile(final String filePath) throws IOException {
        int count = 0;
        List<String[]> content = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                if (count != 0) {
                    content.add(line.split(","));
                }
                count++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return content;
    }
}
