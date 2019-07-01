package com.sofiauniversity.kpk.combinations;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.sofiauniversity.kpk.data.Invoice;

public class CombinationsGenerator {

    private List<Invoice> allInvoices;
    private List<List<Invoice>> allCombinations;

    /**
     * This constructs all possible combinations of invoices that will cover the requested amount
     * 
     * @param allInvoices
     *            are the invoices with wich we are going to make combinations
     */
    public CombinationsGenerator(List<Invoice> allInvoices) {
        this.allInvoices = allInvoices;
        this.allCombinations = new LinkedList<>();
    }

    public List<List<Invoice>> generateCombinations() {
        for (int i = allInvoices.size() - 1; i > 0; i--) {
            generateForCurrentSize(i);
        }
        return allCombinations;
    }

    private void generateForCurrentSize(int size) {
        Queue<List<Invoice>> paths = new LinkedList<>();
        initializeLists(paths);
        generateCurrentCombinations(paths, size);
    }

    private void initializeLists(Queue<List<Invoice>> paths) {
        for (Invoice i : allInvoices) {
            List<Invoice> list = new LinkedList<>();
            list.add(i);
            paths.add(list);
        }
    }

    private void generateCurrentCombinations(Queue<List<Invoice>> paths, int size) {
        while (!paths.isEmpty()) {
            List<Invoice> path = paths.remove();
            for (Invoice invoice : allInvoices) {
                if (!path.contains(invoice)) {
                    if (path.size() == size) {
                        allCombinations.add(path);
                        continue;
                    }
                    List<Invoice> newPath = new LinkedList<>(path);
                    newPath.add(invoice);
                    paths.add(newPath);
                }
            }
        }
    }
}
