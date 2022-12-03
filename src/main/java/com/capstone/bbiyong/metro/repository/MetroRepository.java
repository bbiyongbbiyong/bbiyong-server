package com.capstone.bbiyong.metro.repository;

import com.capstone.bbiyong.metro.domain.Metro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MetroRepository extends JpaRepository<Metro, Long> {
    Optional<Metro> findByTweetId(Long tweetId);
}