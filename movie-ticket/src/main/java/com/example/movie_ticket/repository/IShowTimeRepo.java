package com.example.movie_ticket.repository;

import com.example.movie_ticket.model.ShowTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IShowTimeRepo extends JpaRepository<ShowTime, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM show_time WHERE deleted = 0 ORDER BY show_date")
    Page<ShowTime> findAllOrOrderByShowDate(Pageable pageable);
    @Query(nativeQuery = true, value = "SELECT * FROM show_time WHERE deleted = 0 AND movie_id =:param ORDER BY show_date")
    Page<ShowTime> findShowTimeByMovieId(@Param(value = "param") Long id, Pageable pageable);
}

