package main.java.com.example.geektrust.services;

import main.java.com.example.geektrust.entities.MetroCard;

public interface IMetroCardService {

    public MetroCard createMetroCard(String id, double balance);

    public MetroCard rechargeMetroCard(MetroCard metroCard, double amount);

}
