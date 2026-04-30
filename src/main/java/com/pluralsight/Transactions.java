package com.pluralsight;

public class Transactions {
    private String date;
    private String time;
    private String description;
    private String vendor;
    private double amount;

    //generated Constructor, strings and double
    public Transactions(String date, String time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }
//getters for Amount, Vendor, Date
    public double getAmount() {
        return amount;}
    public String getVendor() {
        return vendor;}
    public String getDate() {
        return date;}

    //formatting to be  date | time | description | vendor | amount
    public String toString() {
        return date + "|" + time + "|" + description + "|" + vendor + "|" + amount;
    }
}