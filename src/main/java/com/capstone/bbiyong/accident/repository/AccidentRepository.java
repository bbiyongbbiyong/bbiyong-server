package com.capstone.bbiyong.accident.repository;

import com.capstone.bbiyong.accident.domain.Accident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccidentRepository extends JpaRepository<Accident, Long> {
}
