package com.example.movie_ticket.service;

import com.example.movie_ticket.model.ShowTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IShowTimeService {
    ShowTime getShowTimeById(Long id);
    List<ShowTime> getAllShowTime();
    void saveNewShowtime(ShowTime showTime);
    void deleteShowtime(Long id);
    Page<ShowTime> findAllShowtime(Pageable pageable);
    ShowTime findShowtimeById(Long id);
    List<ShowTime> findAll();
    Page<ShowTime> findByMovieId(Long id, Pageable pageable);
}
