package com.example.movie_ticket.ultils;

import java.util.Random;

public class NumberRandom {
    public static String codeRandom(){
        StringBuilder random = new StringBuilder("BK");
        for (int i = 0; i < 6; i++) {
            int number = (int) (Math.random()*10);
            random.append(number);
        }
        return random.toString();
    }
}
