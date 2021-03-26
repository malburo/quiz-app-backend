package com.example.Quiz.Ultility;

import java.util.Random;

public class Codeverify {
    Random rand = new Random();
    int upperBound = 100000;
    int lowerBound = 999999;

    String email;
    final int code = lowerBound + (int)(Math.random() * ((upperBound - lowerBound) + 1));
    public Codeverify(String email){
        this.email=email;
    }
}
