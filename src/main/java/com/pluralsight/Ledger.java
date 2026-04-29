package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

public class Ledger {

    public static void displayAllEntries() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"));
            ArrayList<String> lines = new ArrayList<>();

            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

            reader.close();

            Collections.reverse(lines);

            for (String l : lines) {
                System.out.println(l);
            }

        } catch (Exception e) {
            System.out.println("Error reading file.");
        }
    }
}