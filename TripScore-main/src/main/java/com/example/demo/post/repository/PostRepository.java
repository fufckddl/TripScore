package com.example.demo.post.repository;

import com.example.demo.post.entitiy.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
