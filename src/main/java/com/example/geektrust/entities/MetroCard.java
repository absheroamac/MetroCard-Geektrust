package main.java.com.example.geektrust.entities;

public class MetroCard {

    private String id;
    private double balance;

    public MetroCard(String id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFare(double fare) {
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public double getFare() {
        return balance;
    }

}
