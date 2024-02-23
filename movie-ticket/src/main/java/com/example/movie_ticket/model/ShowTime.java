package com.example.movie_ticket.model;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.persistence.*;
import java.util.Set;

@Entity
public class ShowTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "movie_id",referencedColumnName = "id")
    private Movie movie;
    @Column(name = "show_date",columnDefinition = "DATE")
    private String showDate;
    @Column(name = "start_time",columnDefinition = "TIME")
    private String startTime;
    @Column(name = "end_time",columnDefinition = "TIME")
    private String endTime;
    @Column(name = "room_number")
    private String roomNumber;
    private Float price;
    @ManyToOne
    @JoinColumn(name = "employee_support_id",referencedColumnName = "id")
    private Employee employee;
    @OneToMany(mappedBy = "showTime")
    private Set<Booking> booking;
    private int deleted;

    public ShowTime() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getShowDate() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(showDate, dateTimeFormatter).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public void setShowDate(String showDate) {
        this.showDate = showDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Set<Booking> getBooking() {
        return booking;
    }

    public void setBooking(Set<Booking> booking) {
        this.booking = booking;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }
}
