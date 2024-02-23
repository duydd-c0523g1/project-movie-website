package com.example.movie_ticket.service.impl;

import com.example.movie_ticket.model.Movie;
import com.example.movie_ticket.repository.IMovieRepo;
import com.example.movie_ticket.service.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class MovieService implements IMovieService {
    @Autowired
    private IMovieRepo movieRepo;

    @Override
    public List<Movie> getAllMovie() {
        return movieRepo.findAll();
    }

    @Override
    public Movie findMovieById(Long id) {
        if (movieRepo.findById(id).isPresent()) {
        return movieRepo.findById(id).get();
        } else {
            return null;
        }
    }

    @Override
    public Map<Date, List<String>> showTimesMap(Long id) {
        return null;
    }

    @Override
    public void saveMovie(Movie movie) {
        movieRepo.save(movie);
    }

    @Override
    public void deleteMovie(Long id) {
        movieRepo.deleteById(id);
    }

    @Override
    public Page<Movie> findAllMovie(Pageable pageable) {
        return movieRepo.findAll(pageable);
    }

    @Override
    public Page<Movie> findMovieByName(String keyword, Pageable pageable) {
        return movieRepo.findByTitleContaining(keyword, pageable);
    }

    @Override
    public Page<Movie> sortMovieByCategory(Pageable pageable) {
        return movieRepo.findAllOrderByCategory(pageable);
    }

    @Override
    public Page<Movie> findMovieByIdAndName(Long idMovie, String nameMovie, Pageable pageable) {
        if (idMovie == null) {
            return movieRepo.findByTitleContaining(nameMovie, pageable);
        }
        return movieRepo.findByIdAndTitleContaining(idMovie, nameMovie, pageable);
    }

    @Override
    public Page<Movie> findMovieOrderByDate(Pageable pageable) {
        return movieRepo.findAllOrderByDate(pageable);
    }

    @Override
    public Page<Movie> findMoviesByCategoryId(Long id, Pageable pageable) {
        return movieRepo.findMoviesByCategoryId(id, pageable);
    }
}
