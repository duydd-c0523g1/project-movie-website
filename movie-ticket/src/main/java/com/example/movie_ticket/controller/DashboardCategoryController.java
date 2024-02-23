package com.example.movie_ticket.controller;

import com.example.movie_ticket.model.Category;
import com.example.movie_ticket.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/dashboard/category")
public class DashboardCategoryController {
    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public String showDashboardCategory(@PageableDefault(value = 2) Pageable pageable,
                                        Model model) {
        model.addAttribute("categories",categoryService.getAllCategory(pageable));
        return "/category/dashboard-admin-category";
    }

    @GetMapping("/add")
    public ModelAndView showFormAddCategory() {
        return new ModelAndView("/category/create-category","category",new Category());
    }

    @PostMapping("/add")
    public String saveCategory(@ModelAttribute Category category) {
        categoryService.addCategory(category);
        return "redirect:/dashboard/category";
    }


    @GetMapping("/view/{id}")
    public ModelAndView viewCategory(@PathVariable Long id) throws Exception {
        Optional<Category> categoryOptional = categoryService.findById(id);
        if (!categoryOptional.isPresent()) {
            throw new Exception("Lỗi đường dẫn");
        }
        return new ModelAndView("/category/view-category","category",categoryOptional.get());
    }


    @GetMapping("/edit/{id}")
    public ModelAndView showFormAddCategory(@PathVariable Long id) throws Exception {
        Optional<Category> categoryOptional = categoryService.findById(id);
        if (!categoryOptional.isPresent()) {
            throw new Exception("");
        }
        return new ModelAndView("/category/edit-category","category",categoryOptional.get());
    }

    @PostMapping("/edit/{id}")
    public String updateCategory(@ModelAttribute Category category) {
        categoryService.addCategory(category);
        return "redirect:/dashboard/category";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/dashboard/category";
    }

    @GetMapping("/search")
    public ModelAndView searchCategory(@PageableDefault(value = 2) Pageable pageable,
                                       @RequestParam(name = "idCategory",defaultValue = "0") Long idCategory,
                                       String nameCategory, Model model){
        Page<Category> categories = categoryService.searchByIdAndName(idCategory,nameCategory,pageable);
        model.addAttribute("nameCategory",nameCategory);
        if (idCategory == 0) {
            model.addAttribute("idCategory",null);
        }
        return new ModelAndView("/category/dashboard-admin-category","categories",categories);
    }

}
