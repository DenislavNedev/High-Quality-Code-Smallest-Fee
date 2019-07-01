package com.sofiauniversity.kpk.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class DueDateTest {

    @Test
    public void test_creating_date() {
        DueDate date = new DueDate("2018-11-07");
        assertNotNull(date);
    }

    @Test
    public void test_total_days_since_today() {
        DueDate date = new DueDate("2018-11-07");
        long days = 1;
        assertEquals(date.getTotalDaysSinceToday(), days);
    }
}
