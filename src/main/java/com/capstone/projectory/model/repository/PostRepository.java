package com.capstone.projectory.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.capstone.projectory.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
