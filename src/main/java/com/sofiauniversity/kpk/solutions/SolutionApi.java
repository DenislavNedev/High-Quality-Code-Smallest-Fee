package com.sofiauniversity.kpk.solutions;

import java.math.BigDecimal;
import java.util.List;

import com.sofiauniversity.kpk.data.Invoice;

public interface SolutionApi {

    public void findSolution();

    public List<Invoice> getSelectedInvoices();

    public BigDecimal getMinimumAmount();

    public BigDecimal getMinimumFee();

    public void printSolution();
}
