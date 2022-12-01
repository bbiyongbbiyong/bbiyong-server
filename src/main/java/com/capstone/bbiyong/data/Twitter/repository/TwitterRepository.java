package com.capstone.bbiyong.data.Twitter.repository;

import com.capstone.bbiyong.data.Twitter.domain.Twitter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TwitterRepository extends JpaRepository<Twitter, Long> {
}