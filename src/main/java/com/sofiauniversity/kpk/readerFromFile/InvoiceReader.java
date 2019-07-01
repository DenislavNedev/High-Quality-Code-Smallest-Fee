package com.sofiauniversity.kpk.readerFromFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

import com.sofiauniversity.kpk.data.DueDate;
import com.sofiauniversity.kpk.data.Invoice;

public class InvoiceReader {

    public static List<Invoice> createListOfInvoices(String filePath) throws IOException {

        List<Invoice> invoices = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            while (line != null) {
                invoices.add(createInvoice(line));
                line = reader.readLine();
            }
        }
        return invoices;
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
