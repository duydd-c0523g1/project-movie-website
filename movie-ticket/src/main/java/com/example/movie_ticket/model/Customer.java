package com.example.movie_ticket.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "email",unique = true)
    private String email;
    private String phoneNumber;
    private String birthday;
    private String idCard;
    private String address;
    private boolean statis = true;
    @OneToOne
    @JoinColumn(name = "account_id",referencedColumnName = "id")
    private Account account;

    @OneToMany(mappedBy = "customer")
    private Set<Booking> bookings;

    public Customer() {
    }

    public Customer(Long id, String fullName, String email, String phoneNumber, String birthday, String idCard, String address, Account account, Set<Booking> bookings) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.idCard = idCard;
        this.address = address;
        this.account = account;
        this.bookings = bookings;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

    public boolean isStatis() {
        return statis;
    }

    public void setStatis(boolean statis) {
        this.statis = statis;
    }
}
