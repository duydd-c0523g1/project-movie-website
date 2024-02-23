package com.example.movie_ticket.dto;

import com.example.movie_ticket.model.Customer;
import com.example.movie_ticket.model.SeatBooking;
import com.example.movie_ticket.model.ShowTime;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

public class BookingDto implements Validator {
    private Long id;
    private Customer customer;
    private ShowTime showTime;
    private Set<SeatBooking> seatBookings;
    private String codeBooking;
    private LocalDate datePurchased;
    private Float totalPrice;

    public BookingDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ShowTime getShowTime() {
        return showTime;
    }

    public void setShowTime(ShowTime showTime) {
        this.showTime = showTime;
    }

    public Set<SeatBooking> getSeatBookings() {
        return seatBookings;
    }

    public void setSeatBookings(Set<SeatBooking> seatBookings) {
        this.seatBookings = seatBookings;
    }

    public String getCodeBooking() {
        return codeBooking;
    }

    public void setCodeBooking(String codeBooking) {
        this.codeBooking = codeBooking;
    }

    public LocalDate getDatePurchased() {
        return datePurchased;
    }

    public void setDatePurchased(LocalDate datePurchased) {
        this.datePurchased = datePurchased;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        BookingDto bookingDto = (BookingDto) target;
    }
}
