package com.demo.blog.repositry;

import com.demo.blog.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepositry extends JpaRepository<Post, Long> {
}