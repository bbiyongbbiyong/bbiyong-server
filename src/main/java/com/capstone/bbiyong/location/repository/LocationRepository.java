package com.capstone.bbiyong.location.repository;

import com.capstone.bbiyong.location.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
