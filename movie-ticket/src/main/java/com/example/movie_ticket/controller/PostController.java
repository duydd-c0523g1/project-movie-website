package com.example.movie_ticket.controller;

import com.example.movie_ticket.model.Post;
import com.example.movie_ticket.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/dashboard/posts")
public class PostController {
    @Autowired
    private IPostService postService;

    @GetMapping
    public ModelAndView getAllPosts() {
        return new ModelAndView("/post/dashboard-admin-post", "posts", postService.getAllPosts());
    }

    @GetMapping("/add")
    public ModelAndView showCreatePost() {
        return new ModelAndView("/post/create", "post", new Post());
    }

    @PostMapping("/add")
    public String savePost(@ModelAttribute Post post) {
        postService.savePost(post);
        return "redirect:/dashboard/posts";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showFormEditPost(@PathVariable Long id) {
        Post post = postService.getPostById(id);
        return new ModelAndView("/post/edit", "post", post);
    }

    @PostMapping("/edit/{id}")
    public String updatePost(@ModelAttribute Post post) {
        postService.savePost(post);
        return "redirect:/dashboard/posts";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        postService.deletePost(id);
        return "redirect:/dashboard/posts";
    }
}
