package com.pluralsight;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TransactionService {


    //  Saves a transaction by passing it to the file manager.
    public static void saveTransaction(String description,
                                       String vendor,
                                       double amount) {

        TransactionFileManager.saveTransaction(
                description,
                vendor,
                amount);
    }


     // Returns every transaction.
    public static ArrayList<Transactions> getAllTransactions() {
        return TransactionFileManager.loadTransactions();
    }

     // Returns only deposits.
    public static ArrayList<Transactions> getDeposits() {
        ArrayList<Transactions> deposits = new ArrayList<>();
        for (Transactions t : getAllTransactions()) {
            if (t.isDeposit()) {

                deposits.add(t);
            }
        }
        return deposits;
    }

    //Returns only payments.
    public static ArrayList<Transactions> getPayments() {
        ArrayList<Transactions> payments = new ArrayList<>();
        for (Transactions t : getAllTransactions()) {
            if (t.isPayment()) {

                payments.add(t);
            }
        }
        return payments;
    }

     //Searches vendor names.Partial searches are allowed.
    public static ArrayList<Transactions> searchByVendor(String vendor) {
        ArrayList<Transactions> results = new ArrayList<>();
        for (Transactions t : getAllTransactions()) {
            if (t.getVendor()
                    .toLowerCase()
                    .contains(vendor.toLowerCase())) {

                results.add(t);
            }
        }
        return results;
    }

     // Month To Date Report
    public static ArrayList<Transactions> monthToDate() {
        ArrayList<Transactions> results = new ArrayList<>();
        LocalDate today = LocalDate.now();
        for (Transactions t : getAllTransactions()) {
            LocalDate date = LocalDate.parse(t.getDate());
            if (date.getMonth() == today.getMonth()
                    && date.getYear() == today.getYear()) {

                results.add(t);
            }
        }
        return results;

    }


    //  Previous Month Report
    public static ArrayList<Transactions> previousMonth() {

        ArrayList<Transactions> results = new ArrayList<>();
        LocalDate today = LocalDate.now();

        LocalDate firstDay =
                today.minusMonths(1).withDayOfMonth(1);

        LocalDate lastDay =
                today.withDayOfMonth(1).minusDays(1);
        for (Transactions t : getAllTransactions()) {
            LocalDate date = LocalDate.parse(t.getDate());
            if (!date.isBefore(firstDay)
                    && !date.isAfter(lastDay)) {

                results.add(t);
            }
        }
        return results;

    }

     // Year To Date Report
    public static ArrayList<Transactions> yearToDate() {
        ArrayList<Transactions> results = new ArrayList<>();
        LocalDate today = LocalDate.now();

        LocalDate start =
                today.withDayOfYear(1);
        for (Transactions t : getAllTransactions()) {
            LocalDate date = LocalDate.parse(t.getDate());
            if (!date.isBefore(start)
                    && !date.isAfter(today)) {

                results.add(t);
            }
        }
        return results;
    }

     // Previous Year Report
    public static ArrayList<Transactions> previousYear() {
        ArrayList<Transactions> results = new ArrayList<>();
        LocalDate today = LocalDate.now();

        LocalDate start =
                today.minusYears(1).withDayOfYear(1);

        LocalDate end =
                today.withDayOfYear(1).minusDays(1);

        for (Transactions t : getAllTransactions()) {
            LocalDate date = LocalDate.parse(t.getDate());
            if (!date.isBefore(start)
                    && !date.isAfter(end)) {

                results.add(t);
            }
        }
        return results;
    }


    //  Custom Search
    public static ArrayList<Transactions> customSearch(
            String startDate,
            String endDate,
            String description,
            String vendor,
            String amount) {

        ArrayList<Transactions> results = new ArrayList<>();

        LocalDate start = null;
        LocalDate end = null;
        Double searchAmount = null;

        try {
            if (!startDate.isBlank()) {
                start = LocalDate.parse(startDate);
            }
            if (!endDate.isBlank()) {
                end = LocalDate.parse(endDate);
            }
        }
        catch (DateTimeParseException e) {

            System.out.println("❌ Invalid date format. Use yyyy-MM-dd.");
            return results;
        }

        try {
            if (!amount.isBlank()) {
                searchAmount = Double.parseDouble(amount);
            }

        }
        catch (NumberFormatException e) {
            System.out.println("❌ Invalid amount.");
            return results;
        }

        for (Transactions t : getAllTransactions()) {
            LocalDate transactionDate =
                    LocalDate.parse(t.getDate());

            boolean matches = true;
            if (start != null &&
                    transactionDate.isBefore(start)) {
                matches = false;}

            if (end != null &&
                    transactionDate.isAfter(end)) {
                matches = false;}

            if (!description.isBlank()) {
                if (!t.getDescription()
                        .toLowerCase()
                        .contains(description.toLowerCase())) {

                    matches = false;
                }
            }
            if (!vendor.isBlank()) {
                if (!t.getVendor()
                        .toLowerCase()
                        .contains(vendor.toLowerCase())) {

                    matches = false;
                }
            }
            if (searchAmount != null &&
                    t.getAmount() != searchAmount) {

                matches = false;
            }
            if (matches) {
                results.add(t);

            }
        }
        return results;

    }

}