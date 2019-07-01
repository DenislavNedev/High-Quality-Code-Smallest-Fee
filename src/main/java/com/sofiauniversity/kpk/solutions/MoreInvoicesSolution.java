package com.sofiauniversity.kpk.solutions;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.sofiauniversity.kpk.data.Constants;
import com.sofiauniversity.kpk.data.Invoice;

public class MoreInvoicesSolution implements SolutionApi {
    private List<Invoice> invoices;
    private List<Invoice> selectedInvoices;
    private BigDecimal minimumAmount;
    private BigDecimal minimumFee;

    /**
     * This constructs MoreInvoicesSolution which finds which of the two greedy algoritms is better
     * 
     * @param invoices
     *            are all the invoices from the input file
     */
    public MoreInvoicesSolution(List<Invoice> invoices) {
        this.invoices = invoices;
        minimumFee = new BigDecimal(0);
        minimumAmount = new BigDecimal(Integer.MAX_VALUE);
    }

    @Override
    public void findSolution() {
        ExecutorService service = Executors.newFixedThreadPool(2);
        try {

            List<Callable<List<Invoice>>> callList = new ArrayList<>();
            callList.add(new SolutionByFee(new ArrayList<Invoice>(invoices)));
            callList.add(new SolutionByDueDate(new ArrayList<Invoice>(invoices)));

            List<Future<List<Invoice>>> futures = service.invokeAll(callList);

            List<Invoice> solutionByFeeResult = futures.get(0).get(1000, TimeUnit.MILLISECONDS);
            List<Invoice> solutionByDueDateResult = futures.get(1).get(1000, TimeUnit.MILLISECONDS);

            if (compareSolutions(solutionByFeeResult, solutionByDueDateResult)) {
                selectedInvoices = solutionByFeeResult;
            } else {
                selectedInvoices = solutionByDueDateResult;
            }
            calculateMinimumFee();
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        } finally {
            service.shutdown();
        }
    }

    private void calculateMinimumFee() {
        for (Invoice invoice : selectedInvoices) {
            minimumFee = minimumFee.add(invoice.getFee());
        }
    }

    private BigDecimal calculateAmountOfSolution(List<Invoice> invoices) {
        BigDecimal amountOfSolution = new BigDecimal(0);
        for (Invoice invoice : invoices) {
            amountOfSolution = amountOfSolution.add(invoice.getAmount());
        }
        if (amountOfSolution.compareTo(minimumAmount) < 0) {
            minimumAmount = amountOfSolution;
        }
        return amountOfSolution;
    }

    private boolean compareSolutions(List<Invoice> solutionByFee, List<Invoice> solutionByDueDate) {
        return calculateAmountOfSolution(solutionByFee).compareTo(calculateAmountOfSolution(solutionByDueDate)) > 0;
    }

    @Override
    public List<Invoice> getSelectedInvoices() {
        if (selectedInvoices == null)
            return new ArrayList<>();
        else
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

    @Override
    public void printSolution() {
        System.out.println("Credit for amount:" + minimumAmount);
        System.out.println("Fees:" + minimumFee);
        System.out.println("Invoices:");
        for (Invoice currentInvoice : selectedInvoices) {
            System.out.println(currentInvoice);
        }
    }

    public static List<Invoice> selectBestInvoices(List<Invoice> sortedListOfInvoices) {
        ArrayList<Invoice> bestInvoicesByFee = new ArrayList<>();
        BigDecimal currentAmount = new BigDecimal(0);

        for (Invoice inv : sortedListOfInvoices) {
            if (currentAmount.compareTo(Constants.CREDIT_AMOUNT) > 0) {
                return bestInvoicesByFee;
            }
            currentAmount = currentAmount.add(inv.getAmount());
            bestInvoicesByFee.add(inv);
        }
        return bestInvoicesByFee;
    }
}
