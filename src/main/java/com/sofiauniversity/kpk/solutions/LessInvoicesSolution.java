package com.sofiauniversity.kpk.solutions;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.sofiauniversity.kpk.combinations.CombinationsGenerator;
import com.sofiauniversity.kpk.data.Constants;
import com.sofiauniversity.kpk.data.Invoice;

public class LessInvoicesSolution implements SolutionApi {
    private List<Invoice> invoices;
    private List<Invoice> selectedInvoices;
    private BigDecimal minimumAmount;
    private BigDecimal minimumFee;

    /**
     * This constructs solution if the invoices are less than n (10)
     * 
     * @param invoices
     *            are all the invoices read from the input file
     */
    public LessInvoicesSolution(List<Invoice> invoices) {
        this.invoices = invoices;
        this.selectedInvoices = new ArrayList<>();
        calculateMaximumFee();
        minimumAmount = new BigDecimal(0);
    }

    @Override
    public void findSolution() {
        List<List<Invoice>> combinations = new CombinationsGenerator(invoices).generateCombinations();

        for (List<Invoice> currentCombination : combinations) {
            BigDecimal currentFee = new BigDecimal(0);
            BigDecimal currentAmount = new BigDecimal(0);

            for (Invoice inv : currentCombination) {
                currentFee = currentFee.add(inv.getFee());
                currentAmount = currentAmount.add(inv.getAmount());
            }
            if (((minimumFee.compareTo(currentFee)) > 0) && ((currentAmount.compareTo(Constants.CREDIT_AMOUNT)) > 0)) {
                minimumFee = currentFee;
                minimumAmount = currentAmount;
                selectedInvoices = currentCombination;
            }
        }
    }

    @Override
    public List<Invoice> getSelectedInvoices() {
        return selectedInvoices;
    }

    @Override
    public BigDecimal getMinimumAmount() {
        return minimumAmount;
    }

    @Override
    public BigDecimal getMinimumFee() {
        return minimumFee;
    }

    private void calculateMaximumFee() {
        minimumFee = new BigDecimal(0);
        for (Invoice currInvoice : invoices) {
            minimumFee = minimumFee.add(currInvoice.getFee());
        }
    }

    @Override
    public void printSolution() {
        System.out.println("Credit for amount:" + minimumAmount);
        System.out.println("Fees:" + minimumFee);
        System.out.println("Invoices:");
        for (Invoice currentInvoice : selectedInvoices) {
            System.out.println(currentInvoice);
        }
    }
}
