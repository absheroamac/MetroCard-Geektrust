package com.example.geektrust.services;

import com.example.geektrust.entities.MetroCard;

public interface IMetroCardService {

    public MetroCard createMetroCard(String id, double balance);

    public MetroCard rechargeMetroCard(MetroCard metroCard, double amount);

}
