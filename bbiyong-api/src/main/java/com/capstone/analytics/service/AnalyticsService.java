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
@RequiredArgsConstructor
public class AnalyticsService {

    private final AccidentRepository accidentRepository;
    private final EmerMsgRepository emerMsgRepository;
    private final MetroRepository metroRepository;

    @Transactional
    public ChartDataResponseDTO getAnalyzedData() {
        Calendar today = Calendar.getInstance();
        today.setTime(new Date());
        Calendar yesterday = Calendar.getInstance();
        yesterday.setTime(new Date());
        yesterday.add(Calendar.DATE, -1);

        int emergencyMsgYdy = emerMsgRepository.countEmerMsgByDate(yesterday.getTime());
        int emergencyMsgTdy = emerMsgRepository.countEmerMsgByDate(today.getTime());
        int metroYdy = metroRepository.countMetroByDate(yesterday.getTime());
        int metroTdy = metroRepository.countMetroByDate(today.getTime());
        int accidentYdy = accidentRepository.countAccidentByDate(yesterday.getTime());
        int accidentTdy = accidentRepository.countAccidentByDate(today.getTime());

        return ChartDataResponseDTO.of(emergencyMsgYdy, emergencyMsgTdy, metroYdy, metroTdy, accidentYdy, accidentTdy);
    }
}
