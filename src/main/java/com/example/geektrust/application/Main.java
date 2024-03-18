package com.example.geektrust.application;

import java.io.IOException;

import com.example.geektrust.controller.Controller;

public class Main {
    public static void main(String[] args) throws IOException {
        Controller controller = new Controller();

        String input = args[0];
        String localTest = "input.txt";
        controller.start(input);
    }
}
