package com.example.movie_ticket.controller.dashboard;

import com.example.movie_ticket.dto.MovieDto;
import com.example.movie_ticket.model.Category;
import com.example.movie_ticket.model.Movie;
import com.example.movie_ticket.service.ICategoryService;
import com.example.movie_ticket.service.IMovieService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/dashboard/movies")
public class MovieDashboardController {
    @Autowired
    private IMovieService movieService;
    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public ModelAndView showMovieLIst(@PageableDefault(value = 6) Pageable pageable) {
        Page<Movie> movies = movieService.findMovieOrderByDate(pageable);
        return new ModelAndView("/movie/movies", "movies", movies);
    }

    @GetMapping("/add")
    public ModelAndView showAddNewMovie(Model model) {
        List<Category> categoryList = categoryService.getAllCategory();
        model.addAttribute("categoryList", categoryList);
        return new ModelAndView("movie/add", "movieDto", new MovieDto());
    }

    @PostMapping("/confirm-add")
    public String addNewMovie(@Valid @ModelAttribute MovieDto movieDto, BindingResult bindingResult, Model model,RedirectAttributes redirectAttributes) {
        new MovieDto().validate(movieDto, bindingResult);
        List<Category> categoryList = categoryService.getAllCategory();
        model.addAttribute("categoryList", categoryList);
        if (bindingResult.hasErrors()) {
            return "/movie/add";
        } else {
            Movie movie = new Movie();
            BeanUtils.copyProperties(movieDto, movie);
            movieService.saveMovie(movie);
            redirectAttributes.addFlashAttribute("success","Thêm phim thành công");
            return "redirect:/dashboard/movies";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteMovie(@PathVariable Long id,RedirectAttributes redirectAttributes) {
        Movie movie = movieService.findMovieById(id);
        movie.setDeleted(1);
        movieService.saveMovie(movie);
        redirectAttributes.addFlashAttribute("success","Xóa phim thành công");
        return "redirect:/dashboard/movies";
    }

    @GetMapping("/search")
    public ModelAndView searchMovie(@RequestParam(name = "idMovie", required = false) Long idMovie,
                                    String nameMovie, String date, Model model,
                                    @PageableDefault(value = 2) Pageable pageable) {
        Page<Movie> movies = movieService.findMovieByIdAndName(idMovie, nameMovie, pageable);
        if (movies.isEmpty()) {
            return new ModelAndView("/movie/movies", "empty", "Không có kết quả");
        }
        model.addAttribute("nameMovie", nameMovie);
        model.addAttribute("idMovie", idMovie);
        return new ModelAndView("/movie/movies", "movies", movies);
    }

    @GetMapping("/update/{id}")
    public ModelAndView showUpdateMovie(@PathVariable Long id, Model model) {
        Movie movie = movieService.findMovieById(id);
        MovieDto movieDto = new MovieDto();
        BeanUtils.copyProperties(movie, movieDto);
        List<Category> categoryList = categoryService.getAllCategory();
        model.addAttribute("categoryList", categoryList);
        return new ModelAndView("/movie/update", "movieDto", movieDto);
    }

    @PostMapping("/confirm-update")
    public ModelAndView updateMovie(@Valid @ModelAttribute(name = "movieDto") MovieDto movieDto, BindingResult bindingResult,
                                    @RequestParam Long id, Model model,
                                    RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            List<Category> categoryList = categoryService.getAllCategory();
            model.addAttribute("categoryList", categoryList);
            return new ModelAndView("/movie/update", "movieDto", movieDto);
        } else {
            Movie movieTarget = new Movie();
            BeanUtils.copyProperties(movieDto, movieTarget);
            movieService.saveMovie(movieTarget);
            redirectAttributes.addFlashAttribute("success","Cập nhật phim thành công");

            return new ModelAndView("redirect:/dashboard/movies");
        }
    }

    @GetMapping("/detail/{id}")
    public ModelAndView viewMovieDetail(@PathVariable Long id) {
        Movie movie = movieService.findMovieById(id);
        return new ModelAndView("/movie/details", "movie", movie);
    }
}
