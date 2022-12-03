package com.capstone.bbiyong.emerMsg.respository;

import com.capstone.bbiyong.emerMsg.domain.EmerMsg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmerMsgRepository extends JpaRepository<EmerMsg, Long> {
    Optional<EmerMsg> findByEmerMsgId(Long emerMsgId);
}