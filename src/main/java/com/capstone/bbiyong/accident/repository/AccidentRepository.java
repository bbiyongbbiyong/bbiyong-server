package com.capstone.bbiyong.accident.repository;

import com.capstone.bbiyong.accident.domain.Accident;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccidentRepository extends JpaRepository<Accident, Long> {
    Optional<Accident> findByOpenapiId(Long openapiId);
}
