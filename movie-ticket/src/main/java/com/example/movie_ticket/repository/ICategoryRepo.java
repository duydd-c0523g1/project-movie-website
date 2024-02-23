package com.example.movie_ticket.repository;

import com.example.movie_ticket.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICategoryRepo extends JpaRepository<Category,Long> {
    Page<Category> findByIdAndNameCategoryContaining(Long idCategory, String nameCategory,Pageable pageable);
    Page<Category> findByNameCategoryContaining(String nameCategory, Pageable pageable);
}
