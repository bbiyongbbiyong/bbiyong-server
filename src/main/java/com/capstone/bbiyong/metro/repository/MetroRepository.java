package com.capstone.bbiyong.metro.repository;

import com.capstone.bbiyong.metro.domain.Metro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MetroRepository extends JpaRepository<Metro, Long> {
    Optional<Metro> findByOpenapiId(Long openapiId);

    @Query(value = "select m from Metro m where m.startDate <= ?1 and m.endDate >= ?1")
    List<Metro> findAllBy(LocalDateTime now);


    @Modifying
    @Query(value = "delete from Metro m where m.endDate <= :aWeekAgo")
    void deleteAllMetrosByEndDate(@Param("aWeekAgo") LocalDateTime aWeekAgo);
}