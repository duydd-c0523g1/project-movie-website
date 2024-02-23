package com.example.movie_ticket.controller.dashboard;

import com.example.movie_ticket.model.Booking;
import com.example.movie_ticket.service.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/data")
public class APIStatistical {
    @Autowired
    private IBookingService bookingService;
    @GetMapping
    public ResponseEntity<int[]> showDashboard() {
        int[] arr = new int[12];
        int month = 1;
        int count = 0;
        do {
            List<Booking> bookingList = bookingService.showHistoryBookingOfMonth(month);
            for (int i = 0; i < bookingList.size(); i++) {
                arr[count] += bookingList.get(i).getTotalPrice();
            }
            month++;
            count++;
        }
        while (month <= 12);
    return new ResponseEntity<>(arr, HttpStatus.OK);
    }
    @GetMapping("/count")
    public ResponseEntity<int[]> showDashboardCount() {
        int[] count = new int[12];
        int month = 1;
        int mc = 0;
        do {
            List<Booking> bookingList = bookingService.showHistoryBookingOfMonth(month);
            for (int i = 0; i < bookingList.size(); i++) {
                count[mc]++;
            }
            month++;
            mc++;
        }
        while (month <= 12);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
}
