package com.pluralsight;

import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {

        boolean isRunning = true;

        while (isRunning) {
            UI.header("🏦  ACCOUNT LEDGER", "Manage your finances");

            UI.menuOption("D", "Add Deposit");
            UI.menuOption("P", "Make Payment (Debit)");
            UI.menuOption("L", "View Ledger");
            UI.menuOption("X", "Exit");
            UI.blankRow();
            UI.bottomBorder();

            UI.prompt("Choose an option:");
            String choice = scanner.nextLine().toUpperCase().trim();

            switch (choice) {
                case "D":
                    addTransaction(true);
                    break;
                case "P":
                    addTransaction(false);
                    break;
                case "L":
                    Ledger.displayLedger();
                    break;
                case "X":
                    System.out.println();
                    UI.success("Exiting… Have a nice day! 👋");
                    Thread.sleep(800);
                    isRunning = false;
                    break;
                default:
                    UI.error("Invalid option — please try again.");
            }
        }
    }

    // ── Input helpers ──────────────────────────────────────────────

    private static String userInput(String prompt) {
        while (true) {
            UI.prompt(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) return input;
            UI.error("Input cannot be empty. Please try again.");
        }
    }

    private static double userAmount() {
        while (true) {
            try {
                double amount = Double.parseDouble(scanner.nextLine());
                if (amount <= 0) {
                    UI.error("Amount must be greater than 0.");
                    UI.prompt("Amount:");
                    continue;
                }
                return amount;
            } catch (NumberFormatException e) {
                UI.error("Invalid amount — please enter a number.");
                UI.prompt("Amount:");
            }
        }
    }

    private static void addTransaction(boolean isDeposit) {
        UI.header(isDeposit ? "💰  ADD DEPOSIT" : "💸  MAKE PAYMENT",
                  isDeposit ? "Record incoming funds" : "Record an expense");
        UI.blankRow();
        UI.bottomBorder();

        String description = userInput("Description:");
        String vendor      = userInput("Vendor:");
        UI.prompt("Amount:");
        double amount = userAmount();

        if (!isDeposit) {
            amount = -Math.abs(amount);
        }

        TransactionService.saveTransaction(description, vendor, amount);
    }
}
