package com.capstone.projectory.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.capstone.projectory.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
