package com.example.movie_ticket.controller;

import com.example.movie_ticket.model.Category;
import com.example.movie_ticket.model.Movie;
import com.example.movie_ticket.service.ICategoryService;
import com.example.movie_ticket.service.IMovieService;
import com.example.movie_ticket.service.ISeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    private ISeatService seatService;
    @Autowired
    private IMovieService movieService;
    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/detail/{id}")
    public ModelAndView showDetail(@PathVariable Long id){
        return new ModelAndView("detail","movie",movieService.findMovieById(id));
    }

    @GetMapping("/category/{id}")
    public ModelAndView sortMovieByCategory(@PageableDefault(value = 24) Pageable pageable, @PathVariable Long id, Model model) {
        Category category = categoryService.findById(id).get();
        Page<Movie> movies = movieService.findMoviesByCategoryId(category.getId(), pageable);
        model.addAttribute("category", category);
        return new ModelAndView("movie/movie-sorted", "movies", movies);
    }
}
