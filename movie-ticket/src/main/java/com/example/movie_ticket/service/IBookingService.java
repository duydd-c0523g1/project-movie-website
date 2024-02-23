package com.example.movie_ticket.service;

import com.example.movie_ticket.model.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IBookingService {
    void saveBooking(Booking booking);
    Page<Booking> findBookingUsername(String username, Pageable pageable);
    Optional<Booking> findBookingById(Long id);
    void deleteBooking(Long id);
    Page<Booking> showAllBooking(Pageable pageable,String phone,String name);
    Booking findByIdBooking(Long id);
    void cancelBooking(Long id);
    Booking findById(Long id);
    void updateBooking(Booking booking);
    void sendEmail(Booking booking);
    List<Booking> showHistoryBookingDate();
    List<Booking> showHistoryBookingMonth();
    List<Booking> showHistoryBookingYear();
    List<Booking> showHistoryBookingOfMonth(int month);
}
