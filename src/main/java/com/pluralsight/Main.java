package com.pluralsight;

import java.util.Scanner;

public class Main {
    //adding scanner here so it's shared across all objects
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {

        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n====== | 🏦 ACCOUNT LEDGER APP HOME SCREEN 🏦 | ======");

            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment (Debit)");
            System.out.println("L) Ledger 📜");
            System.out.println("X) Exit ");
            System.out.println("\nPlease choose an Option: ");
            String choice = scanner.nextLine().toUpperCase().trim();

            switch (choice) {
                case "D": //deposit info, save to csv
                    addTransaction(true);
                    break;
                case "P": //debit info, save to csv
                    addTransaction(false);
                    break;
                case "L": //displayLedger
                    Ledger.displayLedger();
                    break;
                case "X": //Exit
                    System.out.println("Exiting. . . ");
                    Thread.sleep(1000);
                    System.out.println("👋 Exiting complete. Have a nice day! 👋");
                    isRunning = false;
                    break;

                default:
                    System.out.println("❌ Please Try again, invalid option. ❌");
            }
        }
    }
    // error handling for empty input
    private static String userInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;}
            System.out.println("❌ Input cannot be empty. Please try again. ❌");
        }
    }

    private static double userAmount() {
        while (true) {
            try {
                double amount = Double.parseDouble(scanner.nextLine());

                if (amount <= 0){
                    System.out.println("❌ Amount must be greater than 0. ❌");
                    continue;
                }

                return amount;

            //catches error if user inputs text
            } catch (NumberFormatException e) {
                System.out.print("❌ Invalid amount. Please enter a number: ❌");
            }
        }
    }

    private static void addTransaction(boolean isDeposit) {
        String description = userInput("Description: ");
        String vendor = userInput("Vendor: ");

        System.out.print("Amount: ");
        double amount = userAmount();

        if (!isDeposit) {
            amount = -Math.abs(amount);
        }
        TransactionService.saveTransaction(description, vendor, amount);
    }
}