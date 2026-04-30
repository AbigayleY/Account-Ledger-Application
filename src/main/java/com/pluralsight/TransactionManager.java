package com.pluralsight;

import java.time.format.DateTimeFormatter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class TransactionManager {

    private static final String TCSV = "transactions.csv";


    public static void saveTransaction(String description, String vendor, double amount) {
        try (FileWriter writer = new FileWriter(TCSV, true)) {

            String date = LocalDate.now().toString();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
            String time = LocalTime.now().format(formatter).toString();

            writer.write(date + "|" + time + "|" + description + "|" + vendor + "|" + amount + "\n");

            System.out.println("Transaction saved!");

        } catch (IOException e) {
            System.out.println("Error! Could not write file.");
        }
    }

    public static ArrayList<Transactions> getAllTransactions() {
        ArrayList<Transactions> list = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(TCSV))) {

            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");

                if (parts.length != 5) {
                    System.out.println("Skipping invalid line: " + line);
                    continue;}

                Transactions t = new Transactions(
                        parts[0],
                        parts[1],
                        parts[2],
                        parts[3],
                        Double.parseDouble(parts[4])

                );

                list.add(t);
            }

        } catch (Exception e) {
            System.out.println("Error reading file.");
        }

        return list;
    }

    public static ArrayList<Transactions> getDeposits() {
        ArrayList<Transactions> result = new ArrayList<>();

        for (Transactions t : getAllTransactions()) {
            if (t.getAmount() > 0) {
                result.add(t);
            }
        }

        return result;
    }

    public static ArrayList<Transactions> getPayments() {
        ArrayList<Transactions> result = new ArrayList<>();

        for (Transactions t : getAllTransactions()) {
            if (t.getAmount() < 0) {
                result.add(t);
            }
        }

        return result;
    }

    public static ArrayList<Transactions> searchByVendor(String vendor) {
        ArrayList<Transactions> result = new ArrayList<>();

        for (Transactions t : getAllTransactions()) {
            if (t.getVendor().equalsIgnoreCase(vendor)) {
                result.add(t);
            }
        }
        return result;
    }

    public static ArrayList<Transactions> monthToDate() {
        ArrayList<Transactions> result = new ArrayList<>();
        LocalDate now = LocalDate.now();

        for (Transactions t : getAllTransactions()) {
            LocalDate date = LocalDate.parse(t.getDate());

            if (date.getMonth() == now.getMonth() && date.getYear() == now.getYear()) {
                result.add(t);
            }
        }

        return result;
    }
}