package com.example.movie_ticket.service;

import com.example.movie_ticket.model.SeatBooking;

import java.util.List;

public interface ISeatBookingService {
    List<String> getSeatsOrderedByShowTimes(Long showTimeId);
    void saveSeatBooking(SeatBooking seatBooking);
    List<SeatBooking> getAll();
}
