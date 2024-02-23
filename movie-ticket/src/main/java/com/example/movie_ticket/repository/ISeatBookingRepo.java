package com.example.movie_ticket.repository;

import com.example.movie_ticket.model.Booking;
import com.example.movie_ticket.model.SeatBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
public interface ISeatBookingRepo extends JpaRepository<SeatBooking,Long> {
    @Query(value = "select sb.seat from seat_booking sb join bookings bk on sb.booking_id = bk.id where bk.showtime_id = :param",
    nativeQuery = true)
    List<String> getSeatsOrderedByShowTimes(@Param("param") Long showTimeId);
    List<SeatBooking> findAllByBooking(Booking booking);
}
