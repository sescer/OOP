package ru.nsu.fit.oop.boryapatrushev.patternsearch;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;


public class Tests {
    @Test
    public void test_1() throws Exception {

        Collection<Integer> answer = PatternSearch.findAllOccurrences("test_1_1.txt", "пирог");
        List<Integer> expected = new ArrayList<>();
        expected.add(7);
        Assert.assertEquals(answer, expected);

        answer = PatternSearch.findAllOccurrences("test_1_2.txt", "пирог");
        expected.clear();
        Assert.assertEquals(answer, expected);
    }

    @Test
    public void test_2() throws Exception {

        Collection<Integer> answer = PatternSearch.findAllOccurrences("test_2.txt", "java\r\nwant");
        List<Integer> expected = new ArrayList<>();
        expected.add(21);
        expected.add(38);

        Assert.assertEquals(answer, expected);
    }

    @Test
    public void test_3() throws Exception {

        Collection<Integer> answer = PatternSearch.findAllOccurrences("test_3.txt", "java");
        List<Integer> expected = new ArrayList<>();
        expected.add(21);
        Assert.assertEquals(answer, expected);
    }
    @Test
    public void test_4() throws Exception {

        Collection<Integer> answer = PatternSearch.findAllOccurrences("test_4.txt", "abaf");
        List<Integer> expected = new ArrayList<>();
        expected.add(382);
        Assert.assertEquals(answer, expected);
    }

    @Test
    public void test_5() throws Exception {

        Collection<Integer> answer = PatternSearch.findAllOccurrences("test_5.txt", "bruh");
        List<Integer> expected = new ArrayList<>();

        Assert.assertEquals(answer, expected);
    }
    @Rule
    public ExpectedException exception = ExpectedException.none();
    @Test
    public void test_6() throws Exception {
        exception.expect(IllegalArgumentException.class);
        PatternSearch.findAllOccurrences("test_6.txt", "");
    }

    @Test
    public void test_7() throws Exception {
        String filePath = getFromResources("test_7.txt");
        File gen = new File(filePath);
        gen.createNewFile();
        FileWriter writer= new FileWriter(filePath);
        String str = "I fell for your magic, I tasted your skin " +
                "And though this is tragic, at least I found the end ".repeat(42) +
                "I witnessed your madness, you shed light on my sins ".repeat(42) +
                "And if we share in this sadness, then where have you been? \n".repeat(42);
        List<Integer> expected = new ArrayList<>();
        for (long i = 0; i < ( 1024 * 1024 / str.length()); i++) {
            writer.write(str);
            expected.add((int)i * str.length());
        }
        writer.close();

        Collection<Integer> answer = PatternSearch.findAllOccurrences(  "test_7.txt", "I fell for your magic,");
        Assert.assertEquals(answer, expected);
    }
    private String getFromResources(String fileName) {
        ClassLoader classLoader = PatternSearch.class.getClassLoader();
        return Objects.requireNonNull(classLoader.getResource(fileName)).getPath();
    }

}
