package com.example.movie_ticket.service;

import com.example.movie_ticket.model.Post;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IPostService {
    List<Post> getAllPosts();
    Post getPostById(Long id);
    Post savePost(Post post);
    void deletePost(Long id);
}
