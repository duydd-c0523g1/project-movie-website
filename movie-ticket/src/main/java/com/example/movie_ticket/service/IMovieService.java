package com.example.movie_ticket.service;

import com.example.movie_ticket.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IMovieService {
    List<Movie> getAllMovie();

    Movie findMovieById(Long id);

    Map<Date, List<String>> showTimesMap(Long id);

    void saveMovie(Movie movie);

    void deleteMovie(Long id);

    Page<Movie> findAllMovie(Pageable pageable);

    Page<Movie> findMovieByName(String keyword, Pageable pageable);

    Page<Movie> sortMovieByCategory(Pageable pageable);

    Page<Movie> findMovieByIdAndName(Long idMovie, String nameMovie, Pageable pageable);

    Page<Movie> findMovieOrderByDate(Pageable pageable);
    Page<Movie> findMoviesByCategoryId(Long id, Pageable pageable);
}
