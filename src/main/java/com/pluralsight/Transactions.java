package com.pluralsight;

import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;

public class Transactions {

        public static void addTransaction(String description, String vendor, double amount) {
            try {
                FileWriter writer = new FileWriter("transactions.csv", true);

                //storing the date and time
                String date = LocalDate.now().toString();
                String time = LocalTime.now().toString();

                //adding the format for transactions.csv , close writer and print
                // date | time | description | vendor | amount
                writer.write(date + "|" + time + "|" + description + "|" + vendor + "|" + amount + "\n");
                writer.close();

                System.out.println("Transaction saved!");
            // catch and store error in variable "e"
            } catch (Exception e) {
                System.out.println("Error writing to file.");
            }
        }
    }
