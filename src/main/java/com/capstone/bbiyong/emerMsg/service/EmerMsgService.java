package com.capstone.bbiyong.emerMsg.service;

import com.capstone.bbiyong.emerMsg.domain.EmerMsg;
import com.capstone.bbiyong.emerMsg.respository.EmerMsgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmerMsgService {

    private final EmerMsgRepository emerMsgRepository;

    @Transactional
    public void addEmerMsg(EmerMsg newEmerMsg) {
        Long msgId = newEmerMsg.getEmerMsgId();
        Optional<EmerMsg> emerMsg = emerMsgRepository.findByEmerMsgId(msgId);
        if (emerMsg.isEmpty())
            emerMsgRepository.save(newEmerMsg);
    }
}
