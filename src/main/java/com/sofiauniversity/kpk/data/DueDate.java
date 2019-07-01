package com.sofiauniversity.kpk.data;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DueDate {

    private final int year;
    private final int month;
    private final int day;

    /**
     * This constructs a DueDate of invoice
     * 
     * @param dateFormat
     *            is the date in format 2018-11-26
     */
    public DueDate(String dateFormat) {
        String[] parts = dateFormat.split("-");
        this.year = Integer.parseInt(parts[0]);
        this.month = Integer.parseInt(parts[1]);
        this.day = Integer.parseInt(parts[2]);
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public long getTotalDaysSinceToday() {

        Calendar dueDate = new GregorianCalendar(year, month, day);
        // If the current day is 2018-11-06
        Calendar today = new GregorianCalendar(2018, 11, 6);
        long totalDaysSince = (dueDate.getTimeInMillis() - today.getTimeInMillis()) / (1000 * 60 * 60 * 24);
        return totalDaysSince;
    }

    @Override
    public String toString() {
        return year + "-" + month + "-" + day;
    }

}
