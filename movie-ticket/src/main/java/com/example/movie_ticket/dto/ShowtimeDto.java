package com.example.movie_ticket.dto;

import com.example.movie_ticket.model.Employee;
import com.example.movie_ticket.model.Movie;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.NotBlank;

public class ShowtimeDto implements Validator {
    private Long id;
    @NotBlank(message = "Không được bỏ trống ID phim")
    private Movie movie;
    @NotBlank(message = "Không được bỏ trống ngày chiếu")
    private String showDate;
    @NotBlank(message = "Không được bỏ trống giờ khởi chiếu")
    private String startTime;
    @NotBlank(message = "Không được bỏ trống giờ kết thúc")
    private String endTime;
    @NotBlank(message = "Không được bỏ trống số phòng")
    private String roomNumber;
    @NotBlank(message = "Không được để trống giá tiền")
    private Float price;
    @NotBlank(message = "Không được để trống ID nhân viên")
    private Employee employee;

    public ShowtimeDto() {
    }

    public ShowtimeDto(Long id, Movie movie, String showDate, String startTime, String endTime, String roomNumber, Float price, Employee employee) {
        this.id = id;
        this.movie = movie;
        this.showDate = showDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.roomNumber = roomNumber;
        this.price = price;
        this.employee = employee;
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
        return showDate;
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

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
