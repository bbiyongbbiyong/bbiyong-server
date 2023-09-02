package com.capstone.member.repository;


import com.capstone.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUsername(String username);

    @Query(value = "select m from Member m join Subscribe s where m.notifyOn = true and s.topic = :accident_type")
    List<Member> findAllBySubscribe(@Param("accident_type") String accident_type);
}
