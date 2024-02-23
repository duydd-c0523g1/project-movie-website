package com.example.movie_ticket.repository.impl;

import com.example.movie_ticket.repository.ISeatRepo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SeatRepo implements ISeatRepo {
    private static List<String> seats;
    static {
        seats = new ArrayList<>();
        for (char c = 'A'; c <= 'E'; c++) {
            for (int i = 1; i <= 10; i++) {
                seats.add(c + Integer.toString(i));
            }
        }
    }

    @Override
    public List<String> showSeats() {
        return seats;
    }
}
