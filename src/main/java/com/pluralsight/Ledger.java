package com.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;

public class Ledger {

    //adding Static to scanner so it can scan through all methods
    static Scanner scanner = new Scanner(System.in);

    public static void displayLedger() {
        boolean inLedger = true;


        while (inLedger) {
            System.out.println("\n====== | LEDGER | ======");
            System.out.println("\n Please choose an option: ");

            System.out.println("A) All");
            System.out.println("D) Deposits");
            System.out.println("P) Payments");
            System.out.println("R) Reports");
            System.out.println("H) Home");
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
                    System.out.println("Invalid option.");
            }
        }
    }


    private static void displayEntries(ArrayList<Transactions> list) {
        if (list.isEmpty()) {
            System.out.println("No transactions found.");
            return;}

        for (int i = list.size() - 1; i >= 0; i--) {
            System.out.println(list.get(i));}
    }

    private static void showReports() {
        System.out.println("\n====== REPORTS ======");
        System.out.println("\n Please choose an option: ");

        System.out.println("1) Month To Date");
        System.out.println("2) Previous Month");
        System.out.println("3) Year To Date");
        System.out.println("4) Previous Year");
        System.out.println("5) Search by Vendor");
        System.out.println("0) Back");
        String choice = scanner.nextLine().trim();

        switch (choice) {
            case "1":
                displayEntries(TransactionService.monthToDate());
                break;
            case "5":
                System.out.print("Enter vendor: ");
                String vendor = scanner.nextLine();
                displayEntries(TransactionService.searchByVendor(vendor));
                break;
            case "0":
                return;
            default:
                System.out.println("Please try again, invalid option.");
        }
    }
}