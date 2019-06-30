package com.sofiauniversity.kpk.data;

import java.math.BigDecimal;
import java.util.Comparator;

public class Invoice {

    private final String invoiceID;
    private final DueDate dueDate;
    private final BigDecimal amount;
    private final BigDecimal invoiceFee;

    /**
     * This constructs an invoice
     * 
     * @param invoiceID
     *            is the id of the invoice
     * @param dueDate
     *            is the due date of the invoice
     * @param amount
     *            is the amount of the invoice
     * @param fee
     *            is the fee of the invoice from today to the due date
     */
    public Invoice(String invoiceID, DueDate dueDate, BigDecimal amount, BigDecimal fee) {
        this.invoiceID = invoiceID;
        this.dueDate = dueDate;
        this.amount = amount;
        this.invoiceFee = fee;
    }

    public String getInvoiceID() {
        return invoiceID;
    }

    public DueDate getDueDate() {
        return dueDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getFee() {
        return invoiceFee;
    }

    public static Comparator<Invoice> invoiceFeeComparator = new Comparator<Invoice>() {

        public int compare(Invoice invoice1, Invoice invoice2) {

            return invoice1.getFee().compareTo(invoice2.getFee());
        }
    };

    public static Comparator<Invoice> dueDateComparator = new Comparator<Invoice>() {

        public int compare(Invoice invoice1, Invoice invoice2) {

            return (int) (invoice1.getDueDate().getTotalDaysSinceToday()
                    - invoice2.getDueDate().getTotalDaysSinceToday());
        }
    };

    @Override
    public String toString() {
        return invoiceID + "," + dueDate + "," + amount + "," + invoiceFee;
    }
}
