package ru.nsu.fit.oop.boryapatrushev.gregoriancalendar;
/**
 * class MyGregorianCalendar - is implement of Gregorian calendar
 * based on MyCalendar interface
 */
public class MyGregorianCalendar implements MyCalendar {

    private final int[] monthCodes = { 0, 3, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4 };
    private final int[] cntOfDays = { 31, 28, 31 , 30, 31, 30, 31, 31, 30, 31, 30, 31 };

    @Override
    public MyDate futureDate(MyDate d, int days) throws CloneNotSupportedException, NumberFormatException {

        if (days < 0) throw new NumberFormatException("Days must be positive!");

        MyDate date = d.clone();

        for (int i = 0; i < days; i++) {
            if (date.getYear() % 4 == 0 && date.getYear() % 100 != 0 || date.getYear() % 400 == 0) {
                cntOfDays[1] = 29;
            } else {
                cntOfDays[1] = 28;
            }

            if (date.getDay() == cntOfDays[date.getMonth() - 1]) {
                date.setDay(1);
                if (date.getMonth() == 12) {
                    date.incYear();
                    date.setMonth(1);
                } else {
                    date.incMonth();
                }
            } else {
                date.incDay();
            }
        }

        return date;
    }

    @Override
    public int futureWeekDay(MyDate date, int days) throws CloneNotSupportedException, NumberFormatException {
        if (days < 0) throw new NumberFormatException("Days must be positive!");

        return weekDayByDate(futureDate(date, days));
    }

    @Override
    public int weekDayByDate(MyDate date) throws CloneNotSupportedException {
        MyDate d = date.clone();

        int code = monthCodes[date.getMonth() - 1];
        if (d.getMonth() <= 2) {
            d.decYear();
        }

        return (d.getYear() + d.getYear() / 4 - d.getYear() / 100 + d.getYear() / 400 + code + d.getDay()) % 7;
    }

    @Override
    public int daysTillNewYear(MyDate date) throws CloneNotSupportedException {
        MyDate d = date.clone();
        d.setMonth(1);
        d.setDay(1);
        d.incYear();

        MyDate diff = difference(d, date);

        int days=diff.getDay();
        for (; date.getMonth() < 12; date.incMonth()) {
            days+=cntOfDays[date.getMonth() ];
        }
        return days;
    }

    @Override
    public MyDate friday13(MyDate date) throws CloneNotSupportedException {
        MyDate d = date.clone();
        int weekDay = weekDayByDate(date);

        while (d.getDay() != 13  || weekDay != 5) {
            d = futureDate(d, 1);
            weekDay = weekDayByDate(d);
        }

        return d;
    }

    @Override
    public MyDate difference(MyDate date1, MyDate date2) throws CloneNotSupportedException {
        MyDate d1 = date1.clone();
        MyDate d2 = date2.clone();
        MyDate d = new MyDate();

        if (d1.getDay() >= d2.getDay()) {
            d.setDay(d1.getDay() - d2.getDay());
            helper(d2, d1, d);
        } else {
            if (d1.getMonth() != 1)
                d1.decMonth();
            else
                d1.decYear();
            d.setDay(d1.getDay() + cntOfDays[d1.getMonth() - 1] - d2.getDay());
            helper(d2, d1, d);
        }
        return d;
    }

    private void helper(MyDate d2, MyDate d1, MyDate d) {
        if (d1.getMonth() >= d2.getMonth()) {
            d.setMonth(d1.getMonth() - d2.getMonth());
        } else {
            d1.decYear();
            d.setMonth(d1.getMonth() + 12 - d2.getMonth());
        }
        d.setYear(d1.getYear() - d2.getYear());
    }
}
