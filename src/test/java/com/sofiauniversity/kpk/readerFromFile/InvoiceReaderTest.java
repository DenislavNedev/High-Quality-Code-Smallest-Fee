package com.sofiauniversity.kpk.readerFromFile;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.sofiauniversity.kpk.data.DueDate;
import com.sofiauniversity.kpk.data.Invoice;

public class InvoiceReaderTest {

    @Test
    public void test_is_invoice_created() {

        Invoice invoice = createInvoice("2001,2018-11-26,250");
        assertNotNull(invoice);
    }

    @Test
    public void test_is_list_of_invoices_created_from_file() {
        List<Invoice> invoices;
        try {
            invoices = InvoiceReader.createListOfInvoices("C:\\Users\\dnede\\Desktop\\test.txt");
        } catch (IOException e) {
            invoices = new ArrayList<>();
            e.printStackTrace();
        }
        assertNotNull(invoices);
    }

    private static Invoice createInvoice(String invoiceData) {
        String[] parts = invoiceData.split(",");
        String currentInvoiceId = parts[0];
        DueDate currentDueDate = new DueDate(parts[1]);
        BigDecimal currentAmount = new BigDecimal(parts[2]);

        MathContext mc = new MathContext(4); // 4 precision

        BigDecimal totalDaysSinceToday = new BigDecimal(currentDueDate.getTotalDaysSinceToday());
        BigDecimal currentFee = currentAmount.multiply(new BigDecimal(0.001), mc).multiply(totalDaysSinceToday, mc);

        return new Invoice(currentInvoiceId, currentDueDate, currentAmount, currentFee);
    }
}
