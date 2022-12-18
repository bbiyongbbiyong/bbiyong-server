package com.capstone.bbiyong.accident.repository;

import com.capstone.bbiyong.accident.domain.Accident;
import com.capstone.bbiyong.location.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AccidentRepository extends JpaRepository<Accident, Long> {
    Optional<Accident> findByOpenapiId(Long openapiId);

    @Query(value = "select a from Accident a where a.location = :location " +
            "and a.startDate <= :now and a.endDate >= :now")
    List<Accident> findAllAccidentByLocationAndDate(@Param("location") Location location, @Param("now") Date now);

    @Modifying
    @Query(value = "delete from Accident a where a.endDate <= :aWeekAgo")
    void deleteAllAccidentsByEndDate(@Param("aWeekAgo") Date aWeekAgo);

    @Query(value = "select count(a.location) from Accident a where a.location.id = :locationId " +
            "and a.startDate <= :now and a.endDate >= :now")
    Long countByLocationId(@Param("locationId") Long id, @Param("now") Date now);

    @Query(value = "select accident_type from accident where start_date >= :aWeekAgo " +
            "group by accident_type " +
            "having count(*) = (select max(mycount) from (select accident_type, count(*) as mycount from accident group by accident_type) as result) limit 1", nativeQuery = true)
    Optional<String> findMostAccidentByAccidentType(@Param("aWeekAgo") Date aWeekAgo);

    @Query(value = "select location_id from accident where accident_type = :accidentType and start_date >= :aWeekAgo " +
            "group by location_id " +
            "having count(*) = (select max(mycount) from " +
            "(select location_id, count(*) as mycount from accident " +
            "where accident_type = :accidentType group by location_id) as result) limit 1", nativeQuery = true)
    Optional<Long> findMostLocationByAccidentType(@Param("accidentType") String accidentType, @Param("aWeekAgo") Date aWeekAgo);
}
