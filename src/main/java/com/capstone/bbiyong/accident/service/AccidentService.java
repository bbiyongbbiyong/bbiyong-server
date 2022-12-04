package com.capstone.bbiyong.accident.service;


import com.capstone.bbiyong.accident.domain.Accident;
import com.capstone.bbiyong.accident.repository.AccidentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccidentService {

    private final AccidentRepository accidentRepository;
    @Transactional
    public void addAccident(Accident newAccident) {
        Integer accId = newAccident.getAccId();
        Optional<Accident> accident = accidentRepository.findByAccId(accId);
        if (accident.isEmpty())
            accidentRepository.save(newAccident);
    }
}
