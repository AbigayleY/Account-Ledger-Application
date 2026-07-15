package com.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;

public class Ledger {

    static Scanner scanner = new Scanner(System.in);

    public static void displayLedger() {
        boolean inLedger = true;

        while (inLedger) {
            UI.header("📜  LEDGER", "Transaction history & reports");

            UI.menuOption("A", "View All Transactions");
            UI.menuOption("D", "View Deposits");
            UI.menuOption("P", "View Payments");
            UI.menuOption("R", "Reports");
            UI.menuOption("H", "Home");
            UI.blankRow();
            UI.bottomBorder();

            UI.prompt("Choose an option:");
            String choice = scanner.nextLine().toUpperCase().trim();

            switch (choice) {
                case "A":
                    displayEntries(TransactionService.getAllTransactions(), "ALL TRANSACTIONS");
                    break;
                case "D":
                    displayEntries(TransactionService.getDeposits(), "DEPOSITS");
                    break;
                case "P":
                    displayEntries(TransactionService.getPayments(), "PAYMENTS");
                    break;
                case "R":
                    showReports();
                    break;
                case "H":
                    inLedger = false;
                    break;
                default:
                    UI.error("Invalid option — please try again.");
            }
        }
    }

    // ── Transaction display ────────────────────────────────────────

    private static void displayEntries(ArrayList<Transactions> transactions, String title) {
        System.out.println();
        UI.header("📋  " + title, transactions.size() + " record(s) found");
        UI.bottomBorder();

        if (transactions.isEmpty()) {
            System.out.println();
            UI.error("No transactions found.");
            return;
        }

        UI.tableHeader();

        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transactions t = transactions.get(i);
            UI.transactionRow(
                    t.getDate(),
                    t.getTime(),
                    t.getDescription(),
                    t.getVendor(),
                    t.getAmount()
            );
        }
        System.out.println();
    }

    // ── Reports menu ───────────────────────────────────────────────

    private static void showReports() {
        boolean inReports = true;

        while (inReports) {
            UI.header("✍️  REPORTS", "Filter transactions by period");

            UI.menuOption("1", "Month To Date");
            UI.menuOption("2", "Previous Month");
            UI.menuOption("3", "Year To Date");
            UI.menuOption("4", "Previous Year");
            UI.menuOption("5", "Search by Vendor");
            UI.menuOption("6", "Custom Search");
            UI.menuOption("0", "Back");
            UI.blankRow();
            UI.bottomBorder();

            UI.prompt("Choose an option:");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    displayEntries(TransactionService.monthToDate(), "MONTH TO DATE");
                    break;
                case "2":
                    displayEntries(TransactionService.previousMonth(), "PREVIOUS MONTH");
                    break;
                case "3":
                    displayEntries(TransactionService.yearToDate(), "YEAR TO DATE");
                    break;
                case "4":
                    displayEntries(TransactionService.previousYear(), "PREVIOUS YEAR");
                    break;
                case "5":
                    UI.prompt("Enter vendor name:");
                    String vendor = scanner.nextLine();
                    displayEntries(TransactionService.searchByVendor(vendor),
                            "VENDOR: " + vendor.toUpperCase());
                    break;
                case "6":
                    customSearch();
                    break;
                case "0":
                    inReports = false;
                    break;
                default:
                    UI.error("Invalid option — please try again.");
            }
        }
    }

    // ── Custom search ──────────────────────────────────────────────

    private static void customSearch() {
        UI.header("🔍  CUSTOM SEARCH", "Leave any field blank to skip it");
        UI.blankRow();
        UI.bottomBorder();

        UI.prompt("Start Date (yyyy-MM-dd):");
        String startDate = scanner.nextLine().trim();

        UI.prompt("End Date   (yyyy-MM-dd):");
        String endDate = scanner.nextLine().trim();

        UI.prompt("Description:");
        String description = scanner.nextLine().trim();

        UI.prompt("Vendor:");
        String vendor = scanner.nextLine().trim();

        UI.prompt("Amount:");
        String amount = scanner.nextLine().trim();

        displayEntries(
                TransactionService.customSearch(startDate, endDate, description, vendor, amount),
                "CUSTOM SEARCH RESULTS"
        );
    }
}
