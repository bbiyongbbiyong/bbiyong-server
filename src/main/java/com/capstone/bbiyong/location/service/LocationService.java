package com.capstone.bbiyong.location.service;

import com.capstone.bbiyong.accident.repository.AccidentRepository;
import com.capstone.bbiyong.common.exception.EntityNotFoundException;
import com.capstone.bbiyong.common.exception.ErrorCode;
import com.capstone.bbiyong.emerMsg.respository.EmerMsgRepository;
import com.capstone.bbiyong.location.domain.Location;
import com.capstone.bbiyong.location.repository.LocationRepository;
import com.capstone.bbiyong.metro.repository.MetroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;
    private final AccidentRepository accidentRepository;
    private final EmerMsgRepository emerMsgRepository;
    private final MetroRepository metroRepository;

    HashMap<Long, Long> totalMap = new HashMap<>();

    public Location findLocation(Long id) {
        return locationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.LOCATION_NOT_FOUND));
    }

    public Location findLocationByName(String name) {
        return locationRepository.findByName(name);
    }

    public Long countAccidents(Long id) {
        Date now = new Date();
        return accidentRepository.countByLocationId(id, now);
    }

    public Long countEmerMsgs(Long id) {
        Date now = new Date();
        return emerMsgRepository.countByLocationId(id, now);
    }

    public Long countMetros() {
        LocalDateTime now = LocalDateTime.now();
        return metroRepository.countByLocationId(now);
    }

    public HashMap countTotalEvents() {
        for (Long i = 0l; i < 25l; i++) { // HashMap 초기화
            totalMap.put(i, 0l);
        }
        for (Long i = 0l; i < 25l; i++) {
            totalMap.put(i, countAccidents(i) + countEmerMsgs(i) + countMetros());
        }
        return totalMap;
    }

}
