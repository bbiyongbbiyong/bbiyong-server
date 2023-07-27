package com.capstone.metro.repository;

import com.capstone.metro.domain.Metro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MetroRepository extends JpaRepository<Metro, Long> {
    Optional<Metro> findByOpenapiId(Long openapiId);

    @Query(value = "select m from Metro m where m.startDate <= ?1 and m.endDate >= ?1 " +
                    "order by m.startDate DESC")
    List<Metro> findAllBy(LocalDateTime now);

    @Modifying
    @Query(value = "delete from Metro m where m.endDate <= :aWeekAgo")
    void deleteAllMetrosByEndDate(@Param("aWeekAgo") LocalDateTime aWeekAgo);

    @Query(value = "select count(m.text) from Metro m where m.startDate <= :now and m.endDate >= :now")
    Long countByLocationId(@Param("now") LocalDateTime now);

    @Query(value = "select count(*) from Metro m where date(m.startDate) = date(:yesterday)")
    int countYdyMetroByDate(@Param("yesterday") Date yesterday);

    @Query(value = "select count(*) from Metro m where date(m.startDate) = date(now())")
    int countTdyMetroByDate();
}