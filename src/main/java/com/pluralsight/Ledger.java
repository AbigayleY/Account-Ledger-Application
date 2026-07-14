package com.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;

public class Ledger {

    //adding Static to scanner so it can scan through all methods
    static Scanner scanner = new Scanner(System.in);

    public static void displayLedger() {
        boolean inLedger = true;


        while (inLedger) {
            System.out.println("\n====== | 📜LEDGER📜 | ======");
            System.out.println("\n Please choose an option: ");

            System.out.println("A) View All Transactions");
            System.out.println("D) View Deposits");
            System.out.println("P) View Payments");
            System.out.println("R) Reports");
            System.out.println("H) Home");
            System.out.println("\nChoose an option: ");
            String choice = scanner.nextLine().toUpperCase().trim();

            switch (choice) {
                case "A":
                    displayEntries(TransactionService.getAllTransactions());
                    break;
                case "D":
                    displayEntries(TransactionService.getDeposits());
                    break;
                case "P":
                    displayEntries(TransactionService.getPayments());
                    break;
                case "R":
                    showReports();
                    break;
                case "H":
                    inLedger = false;
                    break;
                default:
                    System.out.println("❌ Invalid option. ❌");
            }
        }
    }

    //Display transactions from newest to oldest
    private static void displayEntries(ArrayList<Transactions> transactions) {
        if (transactions.isEmpty()) {
            System.out.println("\nNo transactions found.🫥");
            return;}

        System.out.println("\n======= TRANSACTIONS =======");
        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transactions t = transactions.get(i);

            System.out.printf(
                    "%-12s %-10s %-20s %-20s $%10.2f%n",
                    t.getDate(),
                    t.getTime(),
                    t.getDescription(),
                    t.getVendor(),
                    t.getAmount());
        }
    }

//Display Reports
    private static void showReports() {
        boolean inReports = true;

        while (inReports) {
            System.out.println("\n====== | ✍️REPORTS✍️ | ======");
            System.out.println("\n Please choose an option: ");

            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("0) Back");
            System.out.println("\nChoose and option: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    displayEntries(TransactionService.monthToDate());
                    break;

                case "2":
                    displayEntries(TransactionService.previousMonth());
                    break;

                case "3":
                    displayEntries(TransactionService.yearToDate());
                    break;

                case "4":
                    displayEntries(TransactionService.previousYear());
                    break;

                case "5":
                    System.out.print("Enter vendor: ");
                    String vendor = scanner.nextLine();
                    displayEntries(TransactionService.searchByVendor(vendor));
                    break;

                case "6":
                    customSearch();
                    break;

                case "0":
                    return;
                default:
                    System.out.println("❌ Please try again, invalid option. ❌");
            }
        }
    }

    //Bonus Custom Search feature
    private static void customSearch(){
        System.out.println("\n ========= CUSTOM SEARCH =========");
        System.out.println("Leave a field blank to ignore it.\n");


        System.out.println("Start Date (yyyy-MM-dd): ");
        String startDate = scanner.nextLine().trim();

        System.out.println("End Date (yyyy-MM-dd): ");
        String endDate = scanner.nextLine().trim();

        System.out.println("Description: ");
        String description = scanner.nextLine().trim();

        System.out.println("Vendor: ");
        String vendor = scanner.nextLine().trim();

        System.out.println("Amount: ");
        String amount = scanner.nextLine().trim();

        displayEntries(TransactionService.customSearch(
                startDate,
                endDate,
                description,
                vendor,
                amount));
    }

}