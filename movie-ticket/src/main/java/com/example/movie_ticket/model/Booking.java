package com.example.movie_ticket.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.Set;

@Entity(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "showtime_id", referencedColumnName = "id")
    private ShowTime showTime;
    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    private Set<SeatBooking> seatBookings;
    @Column(columnDefinition = "int(1) default 0")
    private boolean isDeleted;
    private String codeBooking;
    @Column(columnDefinition = "DATE")
    private LocalDate datePurchased;
    private Float totalPrice;

    public Booking() {
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

    @PrePersist
    public void prePersist() {
        this.datePurchased = LocalDate.now();
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public String getCodeBooking() {
        return codeBooking;
    }

    public void setCodeBooking(String codeBooking) {
        this.codeBooking = codeBooking;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
