package org.fundacionjala.salesforce.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * [SL] this class contains utilities for files.
 */
public final class FileUtils {

    /**
     * [SL] Constructor.
     */
    private FileUtils() {

    }

    /**
     * [SL] Gets a list of the all files inside a directory.
     *
     * @param pathFile      path of the folder
     * @param name          name of the file
     * @param expectedFiles number of files to return
     * @return a list with the file names
     */
    public static List<String> listOfFiles(final String pathFile, final String name, final int expectedFiles) {
        List<String> results = new ArrayList<String>();
        File[] files = new File(pathFile).listFiles();
        int numberFiles = 0;
        for (File file : files) {
            if (file.isFile() && file.getName().contains(name.toLowerCase())) {
                results.add(pathFile + file.getName());
                numberFiles++;
            }
        }
        if (numberFiles == expectedFiles) {
            return results;
        } else {
            return new ArrayList<String>();
        }
    }

    /**
     * [SL] Removes a file.
     *
     * @param filePath
     * @throws IOException
     */
    public static void deleteFile(final String filePath) throws IOException {
        Files.delete(new File(String.format("%s", filePath)).toPath());
    }
}
