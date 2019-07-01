package com.sofiauniversity.kpk.data;

import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.junit.Test;

public class InvoiceTest {

    @Test
    public void test_is_created_invoice() {
        Invoice invoice = new Invoice("1002", new DueDate("2018-12-11"), new BigDecimal(200), new BigDecimal(10));
        assertNotNull(invoice);
    }
}
