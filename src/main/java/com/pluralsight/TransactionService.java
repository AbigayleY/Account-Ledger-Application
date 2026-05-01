package com.pluralsight;

import java.time.format.DateTimeFormatter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class TransactionService {


    public static void saveTransaction(String description, String vendor, double amount) {
        try (FileWriter writer = new FileWriter("transactions.csv", true)) {

            String date = LocalDate.now().toString();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
            String time = LocalTime.now().format(formatter);

            writer.write(date + "|" + time + "|" + description + "|" + vendor + "|" + amount + "\n");

            System.out.println("Transaction saved!✅");

        } catch (IOException e) {
            System.out.println("❌ Error! Could not write file. ❌");
        }
    }
    //referencing workbook 3a Arraylist<Transaction> . . .
    public static ArrayList<Transactions> getAllTransactions() {
        ArrayList<Transactions> list = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"))) {

            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");

                // 5 lines of data! in transactions.csv
                if (parts.length != 5) {
                    System.out.println(" Skipping invalid line: 🏃‍♀️‍➡️" + line);
                    continue;}

                //uses said 5 values and the last part is a double! $$
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
            System.out.println(" ❌ Error reading file. ❌");
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
    public static ArrayList<Transactions> previousMonth() {
        ArrayList<Transactions> result = new ArrayList<>();

        LocalDate today = LocalDate.now();

        LocalDate firstDayPrevMonth = today.minusMonths(1).withDayOfMonth(1);
        LocalDate lastDayPrevMonth = today.withDayOfMonth(1).minusDays(1);

        for (Transactions t : getAllTransactions()) {
            LocalDate date = LocalDate.parse(t.getDate());

            if (!date.isBefore(firstDayPrevMonth) && !date.isAfter(lastDayPrevMonth)) {
                result.add(t);
            }
        }

        return result;
    }
    public static ArrayList<Transactions> yearToDate() {
        ArrayList<Transactions> result = new ArrayList<>();

        LocalDate today = LocalDate.now();
        LocalDate startOfYear = today.withDayOfYear(1);

        for (Transactions t : getAllTransactions()) {
            LocalDate date = LocalDate.parse(t.getDate());

            if (!date.isBefore(startOfYear) && !date.isAfter(today)) {
                result.add(t);
            }
        }

        return result;
    }
    public static ArrayList<Transactions> previousYear() {
        ArrayList<Transactions> result = new ArrayList<>();

        LocalDate today = LocalDate.now();

        LocalDate startPrevYear = today.minusYears(1).withDayOfYear(1);
        LocalDate endPrevYear = today.withDayOfYear(1).minusDays(1);

        for (Transactions t : getAllTransactions()) {
            LocalDate date = LocalDate.parse(t.getDate());

            if (!date.isBefore(startPrevYear) && !date.isAfter(endPrevYear)) {
                result.add(t);
            }
        }

        return result;
    }
}