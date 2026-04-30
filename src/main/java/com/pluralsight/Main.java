package com.pluralsight;

import java.util.Scanner;

public class Main {
    //adding scanner here so it's shared across all objects
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n====== | HOME SCREEN | ======");
            System.out.println("\nPlease choose an Option: ");

            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment (Debit)");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            String choice = scanner.nextLine().toUpperCase().trim();

            switch (choice) {
                case "D": //deposit info, save to csv
                    addDeposit();
                    break;
                case "P": //debit info, save to csv
                    addPayment();
                    break;
                case "L": //displayLedger
                    Ledger.displayLedger();
                    break;
                case "X": //Exit
                    System.out.println("Exiting. . . ");
                    System.out.println("Exiting complete. Have a nice day!");
                    isRunning = false;
                    break;

                default:
                    System.out.println("Please Try again, invalid option.");
            }
        }
    }


    private static void addDeposit() {

        System.out.print("Description of item: ");
        String description = scanner.nextLine();

        System.out.print("Vendor's Name: ");
        String vendor = scanner.nextLine();

        System.out.print("Amount: "); //parseDouble converts String into Double(Decimal point or price)
        double amount = Double.parseDouble(scanner.nextLine());

        TransactionManager.saveTransaction(description, vendor, amount);
    }

    private static void addPayment() {
        System.out.print("Description: ");
        String description = scanner.nextLine();

        System.out.print("Vendor: ");
        String vendor = scanner.nextLine();

        System.out.print("Amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        //
        TransactionManager.saveTransaction(description, vendor, -Math.abs(amount));
    }
}