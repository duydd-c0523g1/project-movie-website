package com.example.movie_ticket.controller.dashboard;

import com.example.movie_ticket.dto.ShowtimeDto;
import com.example.movie_ticket.model.Employee;
import com.example.movie_ticket.model.Movie;
import com.example.movie_ticket.model.ShowTime;
import com.example.movie_ticket.service.IEmployeeService;
import com.example.movie_ticket.service.IMovieService;
import com.example.movie_ticket.service.IShowTimeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/dashboard/showtime")
public class ShowtimeDashboardController {
    @Autowired
    private IShowTimeService showTimeService;
    @Autowired
    private IMovieService movieService;
    @Autowired
    private IEmployeeService employeeService;

    @GetMapping("/view-showtime/{id}")
    public ModelAndView showShowtimeList(@PageableDefault(value = 6) Pageable pageable, @PathVariable Long id, Model model) {
        Page<ShowTime> showTimeList = showTimeService.findByMovieId(id, pageable);
        Movie movie = movieService.findMovieById(id);
        model.addAttribute("movie", movie);
        return new ModelAndView("/showtime/view-showtime",
                "showtimeList", showTimeList);
    }

    @GetMapping("/add/{id}")
    public ModelAndView showAddNewShowtime(Model model, @PathVariable Long id) {
        List<Employee> employeeList = employeeService.findAllEmployee();
        model.addAttribute("employeeList", employeeList);
        ShowtimeDto showtimeDto = new ShowtimeDto();
        showtimeDto.setMovie(movieService.findMovieById(id));
        return new ModelAndView("/showtime/add", "showtimeDto", showtimeDto);
    }
    @PostMapping("/confirm-add")
    public ModelAndView addNewShowtime(@ModelAttribute ShowtimeDto showtimeDto) {
        ShowTime showTime = new ShowTime();
        BeanUtils.copyProperties(showtimeDto, showTime);
        showTimeService.saveNewShowtime(showTime);
        return new ModelAndView("redirect:/dashboard/movies");
    }
    @GetMapping("/update/{id}")
    public ModelAndView showUpdateShowtime(@PathVariable Long id, Model model) {
        ShowTime showTime = showTimeService.findShowtimeById(id);
        List<Employee> employeeList = employeeService.findAllEmployee();
        model.addAttribute("employeeList", employeeList);
        return new ModelAndView("/showtime/update", "showtime", showTime);
    }
    @PostMapping("/confirm-update")
    public ModelAndView updateShowtime(@ModelAttribute ShowTime showTime) {
        showTimeService.saveNewShowtime(showTime);
        return new ModelAndView("redirect:/dashboard/showtime");
    }
    @GetMapping("/delete/{id}")
    public ModelAndView deleteShowtime(@PathVariable Long id) {
        ShowTime showTime = showTimeService.findShowtimeById(id);
        showTime.setDeleted(1);
        showTimeService.saveNewShowtime(showTime);
        return new ModelAndView("redirect:/dashboard/movies");
    }
}
