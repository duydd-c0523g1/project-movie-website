package com.example.movie_ticket.controller.dashboard;

import com.example.movie_ticket.model.Booking;
import com.example.movie_ticket.service.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/dashboard/purchased")
public class DashboardPurchasedUserController {
    @Autowired
    private IBookingService bookingService;

    @GetMapping
    @ResponseBody
    public ModelAndView showDashboardPurchased(@PageableDefault(value = 10,sort = "id",direction = Sort.Direction.DESC) Pageable pageable,
                                               Model model, Principal principal) {
        Page<Booking> bookings = bookingService.findBookingUsername(principal.getName(),pageable);
        model.addAttribute("bookings", bookings);
        return new ModelAndView("/user/dashboard-user-purchased");
    }

    @GetMapping("/view/{id}")
    public ModelAndView viewPurchased(@PathVariable Long id) throws Exception {
        Optional<Booking> bookingOptional = bookingService.findBookingById(id);
        if (!bookingOptional.isPresent()) {
            throw new Exception("Lỗi đường dẫn");
        }
        return new ModelAndView("/user/dashboard-user-purchased-view","booking",bookingOptional.get());
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deletePurchased(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return new ModelAndView("redirect:/dashboard/purchased");
    }
}
