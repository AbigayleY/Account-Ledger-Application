package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TransactionFileManager {

    // File path used throughout the application
    private static final String FILE_NAME = "transactions.csv";

     // Saves a transaction to the CSV file.
    public static void saveTransaction(String description,
                                       String vendor,
                                       double amount) {

        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            String date = LocalDate.now().toString();

            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern("hh:mm a");

            String time =
                    LocalTime.now().format(formatter);

            writer.write(
                    date + "|" +
                            time + "|" +
                            description + "|" +
                            vendor + "|" +
                            amount + "\n"
            );

            System.out.println("\n✅ Transaction saved successfully! ✅");

        }
        catch (IOException e) {

            System.out.println("\n❌ Unable to save transaction. ❌");
        }
    }

     // Reads every transaction from the CSV file.
    public static ArrayList<Transactions> loadTransactions() {
        ArrayList<Transactions> transactions =
                new ArrayList<>();
        try (BufferedReader reader =
                     new BufferedReader(
                             new FileReader(FILE_NAME))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length != 5) {
                    System.out.println(
                            "❌ Skipping invalid line: ❌");
                    System.out.println(line);
                    continue;
                }
                try {
                    Transactions transaction =
                            new Transactions(
                                    parts[0],
                                    parts[1],
                                    parts[2],
                                    parts[3],
                                    Double.parseDouble(parts[4]));

                    transactions.add(transaction);
                }
                catch (NumberFormatException e) {
                    System.out.println(
                            "❌ Invalid amount detected. ❌");
                    System.out.println(line);
                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println(
                    "❌ transactions.csv not found. ❌");
        }
        catch (IOException e) {
            System.out.println(
                    "❌ Unable to read transactions. ❌");
        }
        return transactions;
    }
}