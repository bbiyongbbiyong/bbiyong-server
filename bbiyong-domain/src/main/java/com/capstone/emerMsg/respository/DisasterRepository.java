package com.capstone.emerMsg.respository;

import com.capstone.emerMsg.domain.Disaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DisasterRepository extends JpaRepository<Disaster, Long> {

    @Query(value = "select d.enTopic from Disaster d where :message like CONCAT('%', d.krTopic, '%')")
    Optional<String> findEnTopicByKrTopic(@Param("message") String message);


}
