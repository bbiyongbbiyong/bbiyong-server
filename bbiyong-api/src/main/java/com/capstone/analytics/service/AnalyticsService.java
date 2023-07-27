package com.capstone.analytics.service;

import com.capstone.accident.dto.MostAccidentResponseDTO;
import com.capstone.accident.repository.AccidentRepository;
import com.capstone.analytics.dto.ChartDataResponseDTO;
import com.capstone.emerMsg.respository.EmerMsgRepository;
import com.capstone.metro.repository.MetroRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;

@Service
@Slf4j
@RequiredArgsConstructor
public class AnalyticsService {

    private final AccidentRepository accidentRepository;
    private final EmerMsgRepository emerMsgRepository;
    private final MetroRepository metroRepository;

    @Transactional
    public ChartDataResponseDTO getAnalyzedData() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -1);

        int emergencyMsgYdy = emerMsgRepository.countYdyEmerMsgByDate(cal.getTime());
        int emergencyMsgTdy = emerMsgRepository.countTdyEmerMsgByDate();
        int metroYdy = metroRepository.countYdyMetroByDate(cal.getTime());
        int metroTdy = metroRepository.countTdyMetroByDate();
        int accidentYdy = accidentRepository.countYdyAccidentByDate(cal.getTime());
        int accidentTdy = accidentRepository.countTdyAccidentByDate();

        return ChartDataResponseDTO.of(emergencyMsgYdy, emergencyMsgTdy, metroYdy, metroTdy, accidentYdy, accidentTdy);
    }
}
