package com.capstone.bbiyong.emerMsg.service;

import com.capstone.bbiyong.emerMsg.domain.EmerMsg;
import com.capstone.bbiyong.emerMsg.dto.EmerMsgResponseDTO;
import com.capstone.bbiyong.emerMsg.respository.EmerMsgRepository;
import com.capstone.bbiyong.location.domain.Location;
import com.capstone.bbiyong.location.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmerMsgService {

    private final EmerMsgRepository emerMsgRepository;
    private final LocationService locationService;

    @Transactional
    public void addEmerMsg(EmerMsg newEmerMsg) {
        Long openapiId = newEmerMsg.getOpenapiId();
        Optional<EmerMsg> emerMsg = emerMsgRepository.findByOpenapiId(openapiId);
        if (emerMsg.isEmpty())
            emerMsgRepository.save(newEmerMsg);
    }

    @Transactional
    public List<EmerMsgResponseDTO> getEmerMsg(Long locationId) {
        Location location = locationService.findLocation(locationId);
        Date now = new Date();

        return emerMsgRepository.findAllEmerMsgByLocationAndDate(location, now).stream()
                .map(EmerMsgResponseDTO::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteEmerMsgs() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -7);

        emerMsgRepository.deleteAllEmerMsgsByEndDate(cal.getTime());
    }
}
