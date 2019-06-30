Context
---
A business owner wants to get a credit from a bank secured by a number of invoices.
Each invoice has it's unique number (invoiceId), due date and amount.
The credit fees will be calculated by multiplying the number of remaining days till the due date of each invoice with the bank's daily interest rate.
Given the business owner wants to get credit for $1500, write a program that will find which invoices he can use, so the paid fees are smallest.

The bank fee is 0.1% per day.

Solution details
---
The first step would be to calculate what will be the fee for each invoice. We'll call this field invoiceFee.

In order to find the smallest fee, you have to find all possible combinations of all invoices that cover the given amount. This process will be pretty slow for larger number of invoices, so you have two implement two strategies based on the number of invoices (n).

If n <= 10, generate all possible combinations of invoices that will cover the requested amount.

If n > 10, run in parallel two greedy algoritms:
 - sort the invoices by invoiceFee ascending and adding the invoices with lowest invoice fee until their amount covers the needed one.
 - sort the invoices by amount due date ascending and select the invoices closest to the current day until their amount covers the needed one.
After running these two algorithms in parallel, select the better solution.


Input
---
File that will have one invoice per line in the format:
invoiceId (int),due date (date informat yyyy-mm-dd), amount (big decimal)
See example files.

Output on the console
---
 - Credit Amount
 - Credit Fees
 - Invoices that will be used for the credit request
 
Examples
----
**1. Less than 10 invoices**

If the current day is November 6th.

Input file
```
1001,2018-11-26,750
1002,2018-12-26,1500
1003,2018-12-06,1000
1004,2018-12-26,1000
```

With Calculated fees (this must be calculated by the program)
```
InvoiceId,Due Date,Amount, Calculated Fees
1001,2018-11-26,750,  20 * 0.001 * 750 = 15
1002,2018-12-26,1500, 50 * 0.001 * 1500 = 75
1003,2018-12-06,1000, 30 * 0.001 * 1000 = 30
1004,2018-12-16,2000, 40 * 0.001 * 2000 = 80
```

**Output with the solution**
```
Credit for amount: 1750
Fees: 45
Invoices:
1001,2018-11-26,750
1003,2018-12-06,1000
```

**2. More than 10 invoices**

Input file

```
2001,2018-11-26,250
2002,2018-12-26,150
2003,2018-12-06,100
2004,2018-12-26,100
2005,2018-11-26,250
2006,2018-12-26,150
2007,2018-12-06,100
2008,2018-12-26,100
2009,2018-11-26,250
2010,2018-12-26,150
2011,2018-12-06,100
2012,2018-12-26,100
2013,2018-11-26,250
2014,2018-12-26,150
2015,2018-12-06,100
2016,2018-12-26,100
```

With calculated fees (this must be calculated by the program)

```
2001,2018-11-26,250, 5
2002,2018-12-26,150, 7.5
2003,2018-12-06,100, 3
2004,2018-12-26,100, 5
2005,2018-11-26,250, 5
2006,2018-12-26,150, 7.5
2007,2018-12-06,100, 3
2008,2018-12-26,100, 5
2009,2018-11-26,250, 5
2010,2018-12-26,150, 7.5
2011,2018-12-06,100, 3
2012,2018-12-26,100, 5
2013,2018-11-26,250, 5
2014,2018-12-26,150, 7.5
2015,2018-12-06,100, 3
2016,2018-12-26,100, 5
```
Sorted by invoice fee (this must be calculated by the program)

```
2003,2018-12-06,100, 3
2007,2018-12-06,100, 3
2011,2018-12-06,100, 3
2015,2018-12-06,100, 3
2001,2018-11-26,250, 5
2004,2018-12-26,100, 5
2005,2018-11-26,250, 5
2008,2018-12-26,100, 5
2009,2018-11-26,250, 5
2012,2018-12-26,100, 5
2013,2018-11-26,250, 5
2016,2018-12-26,100, 5
2002,2018-12-26,150, 7.5
2006,2018-12-26,150, 7.5
2010,2018-12-26,150, 7.5
2014,2018-12-26,150, 7.5
```

Sorted by invoice fee solution

```
Credit Amount: 1700
Credit Fees: 47
Invoices: 
2003,2018-12-06,100, 3
2007,2018-12-06,100, 3
2011,2018-12-06,100, 3
2015,2018-12-06,100, 3
2001,2018-11-26,250, 5
2004,2018-12-26,100, 5
2005,2018-11-26,250, 5
2008,2018-12-26,100, 5
2009,2018-11-26,250, 5
2012,2018-12-26,100, 5
2013,2018-11-26,250, 5
```

Sorted by due date (this must be calculated by the program)
```
2001,2018-11-26,250, 5
2005,2018-11-26,250, 5
2009,2018-11-26,250, 5
2013,2018-11-26,250, 5
2003,2018-12-06,100, 3
2007,2018-12-06,100, 3
2011,2018-12-06,100, 3
2015,2018-12-06,100, 3
2002,2018-12-26,150, 7.5
2004,2018-12-26,100, 5
2006,2018-12-26,150, 7.5
2008,2018-12-26,100, 5
2010,2018-12-26,150, 7.5
2012,2018-12-26,100, 5
2014,2018-12-26,150, 7.5
2016,2018-12-26,100, 5
```

Sorted by due date solution

```
Credit Amount: 1550
Credit Fees: 39.5
Invoices: 
2001,2018-11-26,250, 5
2005,2018-11-26,250, 5
2009,2018-11-26,250, 5
2013,2018-11-26,250, 5
2003,2018-12-06,100, 3
2007,2018-12-06,100, 3
2011,2018-12-06,100, 3
2015,2018-12-06,100, 3
2002,2018-12-26,150, 7.5
```

**Output with the solution**

```
Credit Amount: 1550
Credit Fees: 39.5
Invoices: 
2001,2018-11-26,250, 5
2005,2018-11-26,250, 5
2009,2018-11-26,250, 5
2013,2018-11-26,250, 5
2003,2018-12-06,100, 3
2007,2018-12-06,100, 3
2011,2018-12-06,100, 3
2015,2018-12-06,100, 3
2002,2018-12-26,150, 7.5
```
