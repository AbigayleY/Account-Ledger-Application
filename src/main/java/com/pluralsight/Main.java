package com.pluralsight;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner Scanner= new Scanner(System.in);
        boolean isrunning = true;

        while (isrunning) {
            System.out.println("\n====== | HOME SCREEN | =======");
            System.out.println("Hello! Please choose an option: ");

            System.out.println("D) Add Deposit");
            System.out.println("P) Make a Debit Payment");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            String choice = Scanner.nextLine().toUpperCase().trim();

            switch (choice){
                case "D":
                    System.out.println("Add Deposit");
                    break;
                case "P":
                    System.out.println("Make a Debit Payment");
                    break;
                case "L":
                    System.out.println("Here is the Ledger. . . ");
                    Ledger.displayAllEntries();
                    break;
                case "X":
                    System.out.println("Exiting program, Goodbye!");
                    isrunning = false;
                    break;
                default:
                    System.out.println("Invalid option. Please choose an option.");
                    break;
            }
        }

        Scanner.close();
        }


    }
