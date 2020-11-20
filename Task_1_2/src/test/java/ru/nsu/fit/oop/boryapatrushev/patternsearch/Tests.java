package ru.nsu.fit.oop.boryapatrushev.patternsearch;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collection;


public class Tests {
    @Test
    public void test_1() throws Exception {

        Collection answer = PatternSearch.findAllOccurrences("test_1_1.txt", "пирог");
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(7);
        Assert.assertEquals(answer, expected);

        answer = PatternSearch.findAllOccurrences("test_1_2.txt", "пирог");
        expected.clear();
        Assert.assertEquals(answer, expected);
    }

    @Test
    public void test_2() throws Exception {

        Collection answer = PatternSearch.findAllOccurrences("test_2.txt", "java\r\nwant");
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(21);
        expected.add(38);

        Assert.assertEquals(answer, expected);
    }

    @Test
    public void test_3() throws Exception {

        Collection answer = PatternSearch.findAllOccurrences("test_3.txt", "bruh");
        ArrayList<Integer> expected = new ArrayList<>();

        Assert.assertEquals(answer, expected);
    }

    @Test
    public void test_4() throws Exception {
        File gen = new File("src/test/resources/test_4.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(gen));
        for(long i = 0; i != 5000000L; i++) {
            writer.write("ja");
        }

        Collection answer = PatternSearch.findAllOccurrences(  "test_4.txt", "bruh");
        ArrayList<Integer> expected = new ArrayList<>();
        Assert.assertEquals(answer, expected);

        gen.delete();
    }

}
