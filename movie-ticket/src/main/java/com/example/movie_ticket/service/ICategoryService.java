package com.example.movie_ticket.service;


import com.example.movie_ticket.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    List<Category> getAllCategory();
    Page<Category> getAllCategory(Pageable pageable);
    void addCategory(Category category);
    Optional<Category> findById(Long id);
    void deleteCategory(Long id);
    Page<Category> searchByIdAndName(Long idCategory,String nameCategory,Pageable pageable);
}
