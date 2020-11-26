package ru.nsu.fit.oop.boryapatrushev.gregoriancalendar;

interface MyCalendar {

    /**
     * Function to get future date according to current one and number of days
     * @param date input date
     * @param days number of days to count
     * @return MyDate - future date after 'days' days
     * @throws CloneNotSupportedException, NumberFormatException if days are negative
     */
    MyDate futureDate(MyDate date, int days) throws CloneNotSupportedException, NumberFormatException;

    /**
     * Function to get future week day according to current date and number of days
     * @param date input date
     * @param days number of days to count
     * @return int - day number of the corresponding week (0 - Sunday...6 - Saturday)
     * @throws CloneNotSupportedException, NumberFormatException if days are less than zero
     */
    int futureWeekDay(MyDate date, int days) throws CloneNotSupportedException, NumberFormatException;

    /**
     * Function to get week day according to input date
     * @param date input date
     * @return int - day number of the corresponding week(0 - Sunday...6 - Saturday)
     */
    int weekDayByDate(MyDate date) throws CloneNotSupportedException;

    /**
     * Function to get how many days are left until the new year
     * @param date input date
     * @return int - days until new year
     */
    int daysTillNewYear(MyDate date) throws CloneNotSupportedException;

    /**
     * Function to get the nearest friday 13'th
     * @param date input date
     * @return MyDate - date of the nearest friday 13'th
     */
    MyDate friday13(MyDate date) throws CloneNotSupportedException;

    /**
     * Function to get the difference between two dates
     * @param date1 input date1
     * @param date2 input date2
     * @return MyDate - difference between two dates
     */
    MyDate difference(MyDate date1, MyDate date2) throws CloneNotSupportedException;
}