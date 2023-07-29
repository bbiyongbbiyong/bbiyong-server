package com.capstone.notification.repository;

import com.capstone.notification.domain.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {

    List<Subscribe> findByMemberId(Long memberId);
    Subscribe findByMemberIdAndTopic(Long memberId, String topic);

}
