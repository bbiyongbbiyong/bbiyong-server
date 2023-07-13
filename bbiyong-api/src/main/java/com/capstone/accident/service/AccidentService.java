package com.capstone.accident.service;


import com.capstone.accident.Accident;
import com.capstone.accident.dto.AccidentResponseDTO;
import com.capstone.accident.dto.MostAccidentResponseDTO;
import com.capstone.accident.repository.AccidentRepository;
import com.capstone.location.Location;
import com.capstone.location.service.LocationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccidentService {

    private final AccidentRepository accidentRepository;
    private final LocationService locationService;

    @Transactional
    public void addAccident(Accident newAccident) {
        Long openapiId = newAccident.getOpenapiId();
        Optional<Accident> accident = accidentRepository.findByOpenapiId(openapiId);
        if (accident.isEmpty())
            accidentRepository.save(newAccident);
    }

    @Transactional
    public List<AccidentResponseDTO> getAccident(Long locationId) {
        Location location = locationService.findLocation(locationId);
        Date now = new Date();

        return accidentRepository.findAllAccidentByLocationAndDate(location, now).stream()
                .map(AccidentResponseDTO::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteAccidents() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -7);

        accidentRepository.deleteAllAccidentsByEndDate(cal.getTime());
    }

    @Transactional
    public MostAccidentResponseDTO getMostAccident() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -7);

        Optional<String> accidentType = accidentRepository.findMostAccidentByAccidentType(cal.getTime());
        Optional<Long> locationId = accidentRepository.findMostLocationByAccidentType(accidentType.get(), cal.getTime());

        return MostAccidentResponseDTO.from(accidentType.get(), locationId.get());
    }
}
