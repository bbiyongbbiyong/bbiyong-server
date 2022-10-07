package com.capstone.projectory.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.capstone.projectory.model.Follow;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
}
