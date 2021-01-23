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
     * @param pathFile
     * @return a list with the file paths
     */
    public static List<String> listOfFiles(final String pathFile) {
        List<String> results = new ArrayList<String>();
        File[] files = new File(pathFile).listFiles();

        for (File file : files) {
            if (file.isFile()) {
                results.add(pathFile + file.getName());
            }
        }
        return results;
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
