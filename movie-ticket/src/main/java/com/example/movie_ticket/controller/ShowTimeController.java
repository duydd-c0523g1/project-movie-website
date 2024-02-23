package com.example.movie_ticket.controller;

import com.example.movie_ticket.config.ConfigVNPay;
import com.example.movie_ticket.model.*;
import com.example.movie_ticket.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/showtime")
public class ShowTimeController {
    @Autowired
    private ISeatService seatService;
    @Autowired
    private IMovieService movieService;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private ISeatBookingService seatBookingService;
    @Autowired
    private IShowTimeService showTimeService;
    @Autowired
    private IAccountService accountService;
    @Autowired
    private IBookingService bookingService;
    @Autowired
    private ICustomerService customerService;


    @GetMapping("/detail/{id}")
    public String showSeat(@PathVariable Long id, Model model) {
        model.addAttribute("seats", seatService.showSeats());
        model.addAttribute("showTime", showTimeService.getShowTimeById(id));
        model.addAttribute("seatsActive", seatBookingService.getSeatsOrderedByShowTimes(id));
        return "seat";
    }

    @PostMapping("/check-out/{id}")
    @ResponseBody
    public ModelAndView getSelectSeat(@PathVariable Long id,
                                      @RequestParam(name = "seat-select") String[] seats,
                                      Model model, Principal principal) {
        ShowTime showTime = showTimeService.getShowTimeById(id);
        model.addAttribute("seats", seats);
        model.addAttribute("account", accountService.findByUsername(principal.getName()));
        model.addAttribute("showTime", showTime);
        model.addAttribute("booking", new Booking());
        return new ModelAndView("checkout");
    }
}
//else {
//            Customer customerFind = customerService.findCustomerbyId(customer);
//            ShowTime showTimeFind = showTimeService.getShowTimeById(showTimeOrder);
//            Booking booking = new Booking();
//            booking.setCustomer(customerFind);
//            booking.setCodeBooking(NumberRandom.codeRandom());
//            booking.setShowTime(showTimeFind);
//            booking.setTotalPrice(totalPrice);
//            bookingService.saveBooking(booking);
//            for (String seat : seatSelect) {
//                SeatBooking seatBooking = new SeatBooking();
//                seatBooking.setBooking(booking);
//                seatBooking.setSeat(seat);
//                seatBookingService.saveSeatBooking(seatBooking);
//            }
//            return "redirect:/";
//        }
