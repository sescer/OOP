package ru.nsu.fit.oop.boryapatrushev.notebook;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class Tests {
    @Test
    public void test_1() {
        Notebook nb = new Notebook();
        assertTrue(nb.addNote("Title", "Text1"));
        assertFalse(nb.addNote("Title", "Text2"));
        assertEquals(1, nb.stream().count());
        assertEquals("Text1", nb.getRecords().get(0).getBody());
    }

    @Test
    public void test_2() {
        Notebook nb = new Notebook();
        assertTrue(nb.addNote("Title1", "Text1"));
        assertTrue(nb.addNote("Title2", "Text2"));
        assertTrue(nb.addNote("Title3", "Text3"));
        assertEquals(3, nb.stream().count());
        nb.removeNote("Title2");
        assertEquals(2, nb.stream().count());
    }

    @Test
    public void test_3() {
        Notebook nb = new Notebook("src/test/resources/storage.json");
        assertEquals(5, nb.stream().count());

        assertTrue(
                nb.stream().map(Record::getTitle).anyMatch(s -> s.equals("Theory of Probability")));
        assertTrue(nb.stream().map(Record::getTitle).anyMatch(s -> s.equals("WoW")));
        assertTrue(nb.stream().map(Record::getTitle).anyMatch(s -> s.equals("Java")));
        assertTrue(nb.stream().map(Record::getTitle).anyMatch(s -> s.equals("hello")));
    }

    @Test
    public void test_4() {
        File file = new File("src/test/resources/storageTmp.json");
        file.delete();
        Notebook nb = new Notebook("src/test/resources/storageTmp.json");
        assertTrue(nb.addNote("Title1", "Text1"));
        assertTrue(nb.addNote("Title2", "Text2"));
        assertTrue(nb.addNote("Title3", "Text3"));
        try {
            nb.saveNotebook();
        } catch (IOException e) {
            System.err.println(e.toString());
            fail();
        }
        Notebook nbNew = new Notebook("src/test/resources/storageTmp.json");
        assertEquals(3, nbNew.stream().count());
        assertTrue(nbNew.stream().map(Record::getTitle).anyMatch(s -> s.equals("Title1")));
        assertTrue(nbNew.stream().map(Record::getTitle).anyMatch(s -> s.equals("Title2")));
        assertTrue(nbNew.stream().map(Record::getTitle).anyMatch(s -> s.equals("Title3")));
        file.delete();
    }
}