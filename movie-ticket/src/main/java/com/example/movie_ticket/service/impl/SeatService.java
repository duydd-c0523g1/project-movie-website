package com.example.movie_ticket.service.impl;

import com.example.movie_ticket.repository.ISeatRepo;
import com.example.movie_ticket.service.ISeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService implements ISeatService {
    @Autowired
    private ISeatRepo seatRepo;
    @Override
    public List<String> showSeats() {
        return seatRepo.showSeats();
    }
}
