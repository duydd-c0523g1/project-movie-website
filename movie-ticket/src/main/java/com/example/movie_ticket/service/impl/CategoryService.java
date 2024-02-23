package com.example.movie_ticket.service.impl;

import com.example.movie_ticket.model.Category;
import com.example.movie_ticket.repository.ICategoryRepo;
import com.example.movie_ticket.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private ICategoryRepo categoryRepo;
    @Override
    public List<Category> getAllCategory() {
        return categoryRepo.findAll();
    }

    @Override
    public Page<Category> getAllCategory(Pageable pageable) {
        return categoryRepo.findAll(pageable);
    }

    @Override
    public void addCategory(Category category) {
        categoryRepo.save(category);
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepo.findById(id);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepo.deleteById(id);
    }

    @Override
    public Page<Category> searchByIdAndName(Long idCategory, String nameCategroy, Pageable pageable) {
        if (idCategory == 0) {
            return categoryRepo.findByNameCategoryContaining(nameCategroy, pageable);
        }
        return categoryRepo.findByIdAndNameCategoryContaining(idCategory,nameCategroy, pageable);
    }
}
