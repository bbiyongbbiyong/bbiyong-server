package com.capstone.bbiyong.accident.service;


import com.capstone.bbiyong.accident.domain.Accident;
import com.capstone.bbiyong.accident.dto.AccidentResponseDTO;
import com.capstone.bbiyong.accident.repository.AccidentRepository;
import com.capstone.bbiyong.location.domain.Location;
import com.capstone.bbiyong.location.service.LocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
}
