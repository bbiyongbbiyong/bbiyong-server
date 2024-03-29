package com.capstone.location.repository;

import com.capstone.location.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
    Location findByName(String name);
}
