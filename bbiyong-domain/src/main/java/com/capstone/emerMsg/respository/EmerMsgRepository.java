package com.capstone.emerMsg.respository;

import com.capstone.emerMsg.domain.EmerMsg;
import com.capstone.location.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface EmerMsgRepository extends JpaRepository<EmerMsg, Long> {
    Optional<EmerMsg> findByOpenapiId(Long openapiId);

    @Query(value = "select e from EmerMsg e where (e.location = :location " +
            "or e.location.id = 1)" +
            "and e.startDate <= :now and e.endDate >= :now " +
            "order by e.startDate DESC")
    List<EmerMsg> findAllEmerMsgByLocationAndDate(@Param("location") Location location, @Param("now") Date now);

    @Modifying
    @Query(value = "delete from EmerMsg e where e.endDate <= :aWeekAgo")
    void deleteAllEmerMsgsByEndDate(@Param("aWeekAgo") Date aWeekAgo);

    @Query(value = "select count(e.location) from EmerMsg e where e.location.id = :locationId " +
            "and e.startDate <= :now and e.endDate >= :now")
    Long countByLocationId(@Param("locationId") Long id, @Param("now") Date now);

    @Query(value = "select count(e.location) from EmerMsg e where e.location.id = 1" +
            "and e.startDate <= :now and e.endDate >= :now")
    Long countByLocationId1(@Param("now") Date now);

    @Query(value = "select count(*) from EmerMsg e where date(e.startDate) = date(:date)")
    int countEmerMsgByDate(@Param("date") Date date);
}