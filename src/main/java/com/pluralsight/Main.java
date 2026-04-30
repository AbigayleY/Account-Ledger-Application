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

    private static String userInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;}
            System.out.println("Input cannot be empty.");
        }
    }

    private static double userAmount() {
        while (true) {
            try {
                String input = scanner.nextLine();
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.print("Invalid number. Try again: ");
            }
        }
    }

    private static void addDeposit() {

        String description = userInput("Description: ");
        String vendor = userInput("Vendor: ");

        System.out.print("Amount: ");
        double amount = userAmount();

        TransactionManager.saveTransaction(description, vendor, amount);
    }

    private static void addPayment() {
        String description = userInput("Description: ");
        String vendor = userInput("Vendor: ");

        System.out.print("Amount: ");
        double amount = userAmount();

        TransactionManager.saveTransaction(description, vendor, -Math.abs(amount));
    }
}