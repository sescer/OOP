package ru.nsu.fit.oop.boryapatrushev.gregoriancalendar;
/**
 * Base class "MyDate" for date representation
 * Implements cloneable interface easy work with source data.
 */

public class MyDate implements Cloneable {

    private int year, month, day;

    public MyDate(int day, int month, int year) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public MyDate() {
        this.year = 0;
        this.month = 0;
        this.day = 0;
    }

    /**
     * To check equality of current date with input one
     * @return true - if dates equal, otherwise - false.
     */
    @Override
    public boolean equals( Object obj) {
        if (obj == null)
            return false;
        MyDate date = (MyDate) obj;
        return (this.day == date.getDay() && this.month == date.getMonth() && this.year == date.getYear());
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + this.day + this.month + this.year;
        return result;
    }

    /**
     * To create a clone of date
     * @return MyDate - cloned date
     * @throws CloneNotSupportedException if cloning is not supported
     */
    @Override
    public MyDate clone() throws CloneNotSupportedException {
        return (MyDate) super.clone();
    }

    /**
     * To extract day from date
     * @return integer - day of current date
     */
    public int getDay() {
        return this.day;
    }

    /**
     * To extract year from date
     * @return integer - year of current date
     */
    public int getYear() {
        return this.year;
    }

    /**
     * To extract month from date
     * @return integer - month of current date
     */
    public int getMonth() {
        return this.month;
    }

    /**
     * To set day in current date
     * @param day - day to set in date
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     *To set year in current date
     * @param year - year to set in date
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * To set month in current date
     * @param month - month to set in date
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * To increment day in current date
     */
    public void incDay() {
        this.day++;
    }

    /**
     * To increment year in current date
     */
    public void incYear() {
        this.year++;
    }

    /**
     * To increment month in current date
     */
    public void incMonth() {
        this.month++;
    }

    /**
     * To decrement day in current date
     */
    public void decDay() {
        this.day--;
    }

    /**
     * To decrement year in current date
     */
    public void decYear() {
        this.year--;
    }

    /**
     * To decrement month in current date
     */
    public void decMonth() {
        this.month--;
    }

    /**
     * String representation of current date
     * @return string with current date
     */
    @Override
    public String toString() {
        return this.day + "." + this.month + "." + this.year;
    }
}
