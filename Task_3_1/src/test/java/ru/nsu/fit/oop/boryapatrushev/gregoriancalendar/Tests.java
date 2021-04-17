package ru.nsu.fit.oop.boryapatrushev.gregoriancalendar;

import org.junit.Assert;
import org.junit.Test;


public class Tests {
    private final MyGregorianCalendar greg = new MyGregorianCalendar();
    private final MyDate today = new MyDate(20, 11, 2020);

    @Test
    public void test_1() throws CloneNotSupportedException {
        int weekDay = greg.futureWeekDay(today, 1024);
        Assert.assertEquals(weekDay, 0);
    }

    @Test
    public void test_2() throws CloneNotSupportedException {
        MyDate DedWins = new MyDate(9, 5, 1945);
        MyDate actual = new MyDate(11, 6, 75);
        MyDate diff = greg.difference(today, DedWins);

        Assert.assertTrue(actual.equals(diff));
    }

    @Test
    public void test_3() throws CloneNotSupportedException {
        MyDate birth = new MyDate(26, 12, 2000);
        int weekDay = greg.weekDayByDate(birth);

        Assert.assertEquals(2, weekDay);
    }

    @Test
    public void test_4() throws CloneNotSupportedException {
        MyDate future = greg.futureDate(today, 17 * 7);

        Assert.assertEquals(3, future.getMonth());
    }

    @Test
    public void test_5() throws CloneNotSupportedException {
        int days = greg.daysTillNewYear(today);

        Assert.assertEquals(43, days);
    }

    @Test
    public void test_6() throws CloneNotSupportedException {
        MyDate friday = greg.friday13(today);
        MyDate actual = new MyDate(13, 8, 2021);

        Assert.assertTrue(actual.equals(friday));
    }
    @Test
    public void test_7() throws CloneNotSupportedException {
        MyDate FutureDate = new MyDate(26, 12, 2022);
        MyDate diff = greg.difference(FutureDate,today);
        MyDate expected = new MyDate(6, 1, 2);
        Assert.assertTrue(expected.equals(diff));
    }
}
