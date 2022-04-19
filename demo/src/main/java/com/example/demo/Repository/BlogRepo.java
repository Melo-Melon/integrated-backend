package com.example.demo.Repository;


import com.example.demo.model.Blog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepo extends JpaRepository<Blog, Integer> {

    Blog findByBlogId(int blogId);

}
