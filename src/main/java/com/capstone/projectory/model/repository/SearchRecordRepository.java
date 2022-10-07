package com.capstone.projectory.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.capstone.projectory.model.SearchRecord;

@Repository
public interface SearchRecordRepository extends JpaRepository<SearchRecord, Long> {
}
