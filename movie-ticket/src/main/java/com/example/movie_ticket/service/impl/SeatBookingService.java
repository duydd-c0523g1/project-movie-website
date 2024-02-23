package com.example.movie_ticket.service.impl;

import com.example.movie_ticket.model.SeatBooking;
import com.example.movie_ticket.repository.ISeatBookingRepo;
import com.example.movie_ticket.service.ISeatBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatBookingService implements ISeatBookingService {
    @Autowired
    private ISeatBookingRepo seatBookingRepo;

    @Override
    public List<String> getSeatsOrderedByShowTimes(Long showTimeId) {
        return seatBookingRepo.getSeatsOrderedByShowTimes(showTimeId);
    }

    @Override
    public void saveSeatBooking(SeatBooking seatBooking) {
        seatBookingRepo.save(seatBooking);
    }

    public List<SeatBooking> getAll() {
        return seatBookingRepo.findAll();
    }
}
