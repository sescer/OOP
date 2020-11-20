package ru.nsu.fit.oop.boryapatrushev.patternsearch;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class PatternSearch {
    /**
     * Main function of the class
     * Searches for substring occurrences in the entire file
     * @param path File path with text
     * @param substring pattern to search
     * @return Collection with integers - indexes of occurrences
     */
    public static Collection<Integer> findAllOccurrences(String path, String substring) throws Exception {

        String halfstring;
        String string = null;
        int len = substring.length() * 2;
        int scanned = 0;
        ArrayList<Integer> output;
        try (
             InputStream inputStream = PatternSearch.class.getClassLoader().getResourceAsStream(path)) {
            assert inputStream != null;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader( inputStream, StandardCharsets.UTF_8))) {

                String pattern = new String(substring.getBytes(), StandardCharsets.UTF_8);

                Set<Integer> answer = new HashSet<>();

                do {
                    if (scanned == 0) {
                        string = readFixedLengthString(len, reader);
                    } else {
                        halfstring = readFixedLengthString(len / 2, reader);
                        string = string.substring(len / 2);
                        string += halfstring;
                    }

                    ArrayList<Integer> temRes = new ArrayList<>(findMatch(string, pattern));
                    for (int i = 0; i != temRes.size(); i++)
                        temRes.set(i, temRes.get(i) + scanned);

                    answer.addAll(temRes);
                    scanned += len / 2;

                } while (!string.endsWith("\0"));
                output = new ArrayList<>(answer);
                output.sort(Integer::compareTo);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return output;
    }

    /**
     * Function for reading length chars from file and converting them to string
     * @param length number of characters to read
     * @param reader BufferedReader with opened file stream
     * @return scanned string
     * @throws IOException If an I/O error occurs
     */
    private static String readFixedLengthString(int length, BufferedReader reader) throws IOException {
        char[] arr = new char[length];

        try {
            reader.read(arr, 0, length);
        } catch (IOException e) {
            throw new IOException("An error with I/O");
        }
        return new String(arr);
    }

    /**
     * Function for finding pattern occurrences in text
     * @param text Line of text, where occurrences should be found
     * @param pattern Substring to be searched in text
     * @return ArrayList of integers with indexes of occurrences pattern in text
     */
    private static ArrayList<Integer> findMatch(String text, String pattern) {
        ArrayList<Integer> results = new ArrayList<>();
        String string = pattern + '#' + text;
        int len = string.length();
        int[] Z = new int[len];

        zFunc(string, Z);

        for(int i = 0; i!= len; i++) {
            if (Z[i] == pattern.length()) {
                results.add((i - pattern.length() - 1));
            }
        }
        return results;
    }

    /**
     * The idea of Z-function is to concatenate pattern and text
     * Build the Z array for concatenated string.
     * @param string pattern#text for prefix searching
     * @param Z results of z-function (empty in the beginning)
     */

    private static void zFunc(String string, int[] Z) {
        int len = string.length();
        int L = 0, R = 0;

        for(int i = 0; i < len; i++) {

            if(i > R) {
                R = L = i;
                while(R < len && string.charAt(R - L) == string.charAt(R)) R++;
                Z[i] = R - L;
                R--;
            }
            else
            {
                int k = i - L;
                if(Z[k] < R - i + 1)
                    Z[i] = Z[k];
                else
                {
                    L = i;
                    while(R < len && string.charAt(R - L) == string.charAt(R)) R++;
                    Z[i] = R - L;
                    R--;
                }
            }
        }
    }
}
