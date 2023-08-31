package com.capstone.notification.repository;

import com.capstone.member.domain.Member;
import com.capstone.notification.domain.FcmToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FcmTokenRepository extends JpaRepository<FcmToken, Long> {
    @Query(value = "select f from FcmToken f where f.member = :memberId and f.token = :token")
    Optional<FcmToken> findByMemberAndToken(@Param("memberId") Member member, @Param("token") String token);
}
