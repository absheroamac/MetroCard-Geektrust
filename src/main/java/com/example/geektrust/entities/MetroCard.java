package com.example.geektrust.entities;

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

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void addBalance(double amount) {
        this.balance += amount;
    }

    public String getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        MetroCard that = (MetroCard) obj;
        return this.getId() == that.getId() && this.getBalance() == that.getBalance();
    }

}
