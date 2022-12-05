package com.capstone.bbiyong.emerMsg.respository;

import com.capstone.bbiyong.emerMsg.domain.EmerMsg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface EmerMsgRepository extends JpaRepository<EmerMsg, Long> {
    Optional<EmerMsg> findByEmerMsgId(Long emerMsgId);

    @Query(value = "select e from EmerMsg e where e.locationId = ?1 and e.startDateTime <= ?2 and e.endDateTime >= ?2")
    List<EmerMsg> findAllEmerMsgByLocationId(Integer locationId, Date now);
}