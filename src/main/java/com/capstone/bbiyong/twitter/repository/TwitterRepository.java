package com.capstone.bbiyong.twitter.repository;

import com.capstone.bbiyong.twitter.domain.Twitter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TwitterRepository extends JpaRepository<Twitter, Long> {
}