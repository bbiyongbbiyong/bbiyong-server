package com.capstone.bbiyong.metro.repository;

import com.capstone.bbiyong.metro.domain.Metro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MetroRepository extends JpaRepository<Metro, Long> {
    Optional<Metro> findByTweetId(Long tweetId);

    @Query(value = "select m from Metro m where m.startDateTime <= ?1 and m.endDateTime >= ?1")
    List<Metro> findAllBy(LocalDateTime now);
}