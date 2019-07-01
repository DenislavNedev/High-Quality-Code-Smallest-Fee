package com.sofiauniversity.kpk.solutions;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import com.sofiauniversity.kpk.data.Invoice;
import com.sofiauniversity.kpk.readerFromFile.InvoiceReader;

public class BestSolution implements SolutionApi {

    private List<Invoice> invoices;
    private SolutionApi solution;

    /**
     * This constructs BestSolution wich finds the best solution depending on n (the number of the invoices)
     * 
     * @param pathFile
     *            is the path to the input file
     */
    public BestSolution(String pathFile) {
        try {
            invoices = InvoiceReader.createListOfInvoices(pathFile);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("There is an error reading the file");
        }
    }

    @Override
    public void findSolution() {
        if (invoices.size() > 10) {
            solution = new MoreInvoicesSolution(invoices);
        } else {
            solution = new LessInvoicesSolution(invoices);
        }
        solution.findSolution();
    }

    @Override
    public List<Invoice> getSelectedInvoices() {
        return solution.getSelectedInvoices();
    }

    @Override
    public BigDecimal getMinimumAmount() {
        return solution.getMinimumAmount();
    }

    @Override
    public BigDecimal getMinimumFee() {
        return solution.getMinimumFee();
    }

    @Override
    public void printSolution() {
        solution.printSolution();
    }
}
