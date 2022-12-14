package com.capstone.bbiyong.emerMsg.respository;

import com.capstone.bbiyong.emerMsg.domain.EmerMsg;
import com.capstone.bbiyong.location.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface EmerMsgRepository extends JpaRepository<EmerMsg, Long> {
    Optional<EmerMsg> findByOpenapiId(Long openapiId);

    @Query(value = "select e from EmerMsg e where e.location = :location " +
            "and e.startDate <= :now and e.endDate >= :now")
    List<EmerMsg> findAllEmerMsgByLocationAndDate(@Param("location") Location location, @Param("now") Date now);
}