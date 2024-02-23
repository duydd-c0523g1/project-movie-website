package com.example.movie_ticket.controller.dashboard;

import com.example.movie_ticket.model.*;
import com.example.movie_ticket.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
    @Autowired
    private IBookingService bookingService;

    @Autowired
    private IAccountService accountService;

    @GetMapping
    public ModelAndView showDashboard(Principal principal, Model model) {
        List<Booking> bookingDate = bookingService.showHistoryBookingDate();
        List<Booking> bookingMonth = bookingService.showHistoryBookingMonth();
        List<Booking> bookingYear = bookingService.showHistoryBookingYear();
        Float totalDate = 0.0F;
        Float totalMonth = 0.0F;
        Float totalYear = 0.0F;
        int countDate = 0;
        int countMonth = 0;
        int countYear = 0;
        for (int i = 0; i < bookingDate.size(); i++) {
            totalDate += bookingDate.get(i).getTotalPrice();
            countDate++;
        }
        for (int i = 0; i < bookingMonth.size(); i++) {
            totalMonth += bookingMonth.get(i).getTotalPrice();
            countMonth++;
        }
        for (int i = 0; i < bookingYear.size(); i++) {
            totalYear += bookingYear.get(i).getTotalPrice();
            countYear++;
        }
        model.addAttribute("totalDate", totalDate);
        model.addAttribute("totalMonth", totalMonth);
        model.addAttribute("totalYear", totalYear);
        model.addAttribute("countDate", countDate);
        model.addAttribute("countMonth", countMonth);
        model.addAttribute("countYear", countYear);
        return new ModelAndView("dashboard", "account", accountService.findByUsername(principal.getName()));
    }
}