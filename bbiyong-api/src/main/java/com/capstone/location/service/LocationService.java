package com.capstone.location.service;

import com.capstone.accident.repository.AccidentRepository;
import com.capstone.common.exception.EntityNotFoundException;
import com.capstone.common.exception.ErrorCode;
import com.capstone.emerMsg.respository.EmerMsgRepository;
import com.capstone.location.domain.Location;
import com.capstone.location.repository.LocationRepository;
import com.capstone.metro.repository.MetroRepository;
import org.springframework.transaction.annotation.Transactional;
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

    @Transactional
    public Location findLocation(Long id) {
        return locationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.LOCATION_NOT_FOUND));
    }

    @Transactional
    public Location findLocationByName(String name) {
        return locationRepository.findByName(name);
    }

    private Long countAccidents(Long id) {
        Date now = new Date();
        return accidentRepository.countByLocationId(id, now);
    }

    private Long countEmerMsgs(Long id) {
        Date now = new Date();
        return emerMsgRepository.countByLocationId(id, now);
    }

    private Long countEmerMsgsByLocationId1() {
        Date now = new Date();
        return emerMsgRepository.countByLocationId1(now);
    }

    private Long countMetros() {
        LocalDateTime now = LocalDateTime.now();
        return metroRepository.countByLocationId(now);
    }

    @Transactional
    public HashMap countTotalEvents() {
        Long seoulTotal = 0l;

        Long emerMsg1 = countEmerMsgsByLocationId1();
        Long metro = countMetros();

        for (Long i = 1l; i <= 26l; i++) { // HashMap 초기화
            totalMap.put(i, 0l);
        }
        for (Long i = 2l; i <= 26l; i++) {
            totalMap.put(i, countAccidents(i) + countEmerMsgs(i) +emerMsg1 + metro);
            seoulTotal += countAccidents(i) + countEmerMsgs(i);
        }
        seoulTotal += emerMsg1 + metro;
        totalMap.put(1l, seoulTotal);
        return totalMap;
    }

}
