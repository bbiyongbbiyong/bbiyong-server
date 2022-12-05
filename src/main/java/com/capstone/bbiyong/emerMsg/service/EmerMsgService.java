package com.capstone.bbiyong.emerMsg.service;

import com.capstone.bbiyong.emerMsg.domain.EmerMsg;
import com.capstone.bbiyong.emerMsg.dto.EmerMsgResponseDTO;
import com.capstone.bbiyong.emerMsg.respository.EmerMsgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Transactional
    public List<EmerMsgResponseDTO> getEmerMsg(Integer locationId) {
        Date now = new Date();
        return emerMsgRepository.findAllEmerMsgByLocationId(locationId, now).stream()
                .map(EmerMsgResponseDTO::from)
                .collect(Collectors.toList());
    }
}
