package com.sofiauniversity.kpk.solutions;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

import com.sofiauniversity.kpk.data.Invoice;

public class SolutionByDueDate implements Callable<List<Invoice>> {

    private List<Invoice> invoices;

    /**
     * This creates solution when the invoices are sorted by the due date
     * 
     * @param invoices
     *            are all the invoices
     */
    public SolutionByDueDate(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    @Override
    public List<Invoice> call() {
        sort();
        return MoreInvoicesSolution.selectBestInvoices(invoices);
    }

    private void sort() {
        Collections.sort(invoices, Invoice.dueDateComparator);
    }
}
