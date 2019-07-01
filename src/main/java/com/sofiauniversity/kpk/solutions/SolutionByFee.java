package com.sofiauniversity.kpk.solutions;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

import com.sofiauniversity.kpk.data.Invoice;

public class SolutionByFee implements Callable<List<Invoice>> {

    private List<Invoice> invoices;

    /**
     * This creates solution when the invoices are sorted by fee
     * 
     * @param invoices
     *            are all the invoices
     */
    public SolutionByFee(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    @Override
    public List<Invoice> call() {
        sort();
        return MoreInvoicesSolution.selectBestInvoices(invoices);
    }

    private void sort() {
        Collections.sort(invoices, Invoice.invoiceFeeComparator);
    }
}
