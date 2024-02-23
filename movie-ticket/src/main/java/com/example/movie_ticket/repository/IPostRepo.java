package com.example.movie_ticket.repository;

import com.example.movie_ticket.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPostRepo extends JpaRepository<Post,Long> {
}
